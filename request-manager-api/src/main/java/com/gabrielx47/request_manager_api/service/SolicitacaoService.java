package com.gabrielx47.request_manager_api.service;

import com.gabrielx47.request_manager_api.dto.NovaSolicitacaoDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.exception.DataFinalAnteriorADataInicialException;
import com.gabrielx47.request_manager_api.exception.DataNulaException;
import com.gabrielx47.request_manager_api.exception.RecursoNaoEncontradoException;
import com.gabrielx47.request_manager_api.exception.TransicaoDeStatusDaInvalidaException;
import com.gabrielx47.request_manager_api.mapper.SolicitacaoMapper;
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
        SolicitacaoDTO solicitacao = solicitacaoRepository.encontrarSolicitacaoPorId(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada"));

        String statusAtual = solicitacao.getStatus();

        if (!transicaoDeStatusEValida(statusAtual, status)) {
            throw new TransicaoDeStatusDaInvalidaException("Transição de status " + statusAtual + " para " + status + " é inválida");
        }

        solicitacaoRepository.atualizarStatusDaSolicitacao(id, status);
        return "Status da solicitação atualizado com sucesso";
    }

    private boolean transicaoDeStatusEValida(String statusAtual, String novoStatus) {
        if (statusAtual.equals("SOLICITADO") && (novoStatus.equals("LIBERADO") || novoStatus.equals("REJEITADO"))) {
            System.out.println("(SOLICITADO) Transição de status válida: " + statusAtual + " para " + novoStatus);
            return true; 
        } else if (statusAtual.equals("LIBERADO") && (novoStatus.equals("REJEITADO") || novoStatus.equals("APROVADO"))) {
            System.out.println("(LIBERADO) Transição de status válida: " + statusAtual + " para " + novoStatus);
            return true;
        } else if (statusAtual.equals("APROVADO") && novoStatus.equals("CANCELADO")) {
            System.out.println("(APROVADO) Transição de status válida: " + statusAtual + " para " + novoStatus);
            return true;
        } else if (statusAtual.equals("REJEITADO") && statusAtual.equals("CANCELADO")) {
            return false;
        } else{
            // Transição de status para o mesmo status ou transição inválida
            return false;
        }
    }

    public String salvarNovaSolicitacao(NovaSolicitacaoDTO solicitacao) {
        solicitacaoRepository.inserirNovaSolicitacao(solicitacao.getDescricao(), solicitacao.getDataSolicitacao(), solicitacao.getValor(),
         solicitacao.getStatus(), solicitacao.getSolicitante_id(), solicitacao.getCategoria_id());
        return "Solicitação criada com sucesso";
    }
}
