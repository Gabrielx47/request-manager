package com.gabrielx47.request_manager_api.service;

import com.gabrielx47.request_manager_api.dto.NovaSolicitacaoDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.exception.DataFinalAnteriorADataInicialException;
import com.gabrielx47.request_manager_api.exception.DataNulaException;
import com.gabrielx47.request_manager_api.exception.RecursoNaoEncontradoException;
import com.gabrielx47.request_manager_api.exception.TransicaoDeStatusDaInvalidaException;
import com.gabrielx47.request_manager_api.mapper.SolicitacaoMapper;
import com.gabrielx47.request_manager_api.model.entity.Solicitacao;
import com.gabrielx47.request_manager_api.model.entity.Status;
import com.gabrielx47.request_manager_api.projection.SolicitacaoCompletaProjection;
import com.gabrielx47.request_manager_api.projection.SolicitacaoListagemProjection;
import com.gabrielx47.request_manager_api.repository.SolicitacaoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SolicitacaoService {
    private SolicitacaoRepository solicitacaoRepository;
    private SolicitacaoMapper solicitacaoMapper;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository, SolicitacaoMapper solicitacaoMapper) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.solicitacaoMapper = solicitacaoMapper;
    }

    public PagedModel<SolicitacaoListagemDTO> listarParteDasSolicitacoes(String status, LocalDate dataInicio, LocalDate dataFim, String categoria, Pageable pageable) {
        Page<SolicitacaoListagemProjection> solicitacoes;

        if (dataInicio != null) {
            if(dataFim == null) {
                throw new DataNulaException("A data final é obrigatória.");
            } else if (dataFim.isBefore(dataInicio)) {
                throw new DataFinalAnteriorADataInicialException("A data final não pode ser anterior à data inicial.");
            }
        } else if (dataFim != null) {
            throw new DataNulaException("A data inicial é obrigatória.");
        }

        if (status != null && categoria != null && dataInicio != null && dataFim != null) {
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosStatusPeriodoCategoria(status, dataInicio, dataFim, categoria, pageable);
        } else if (status != null && dataInicio != null && dataFim != null) {
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosStatusPeriodo(status, dataInicio, dataFim, pageable);
        } else if (status != null && categoria != null) { 
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosStatusCategoria(status, categoria, pageable);
        } else if (categoria != null && dataInicio != null && dataFim != null) {
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosCategoriaPeriodo(categoria, dataInicio, dataFim, pageable);
        } else if (status != null) {
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesComFiltroStatus(status, pageable);
        } else if (dataInicio != null && dataFim != null) {
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesComFiltroPeriodo(dataInicio, dataFim, pageable);
        } else if (categoria != null) {
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesComFiltroCategoria(categoria, pageable);
        } else {
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesSemFiltro(pageable);
        }

        return new PagedModel<SolicitacaoListagemDTO>(solicitacaoMapper.mapearPageSolicitacaoListagemProjectionParaSolicitacaoListagemDTO(solicitacoes));
    }

    public SolicitacaoCompletaDTO encontrarTodosOsDadosDaSolicitacao(Long id) {
        SolicitacaoCompletaProjection solicitacaoCompleta =solicitacaoRepository.selecionarTodosDadosDaSolicitacaoPorId(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada"));
        return solicitacaoMapper.mapearSolicitacaoCompletaProjectionParaSolicitacaoCompletaDTO(solicitacaoCompleta);
    }

    public String atualizarStatusDaSolicitacao(Long id, String status) {
        Solicitacao solicitacao = solicitacaoRepository.encontrarSolicitacaoPorId(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada"));

        Status statusAtual = solicitacao.getStatus();

        if (!statusAtual.transicaoValida(status)) {
            throw new TransicaoDeStatusDaInvalidaException(
                    "Transição de status " + statusAtual.getNome() + " para " + status + " é inválida");
        }

        solicitacaoRepository.atualizarStatusDaSolicitacao(id, status);
        return "Status da solicitação atualizado com sucesso";
    }

    public String salvarNovaSolicitacao(NovaSolicitacaoDTO solicitacao) {
        solicitacaoRepository.inserirNovaSolicitacao(solicitacaoMapper.mapearNovaSolicitacaoDTOParaSolicitacao(solicitacao));
        return "Solicitação criada com sucesso";
    }
}
