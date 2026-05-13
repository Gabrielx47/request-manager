package com.gabrielx47.request_manager_api.service;

import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.exception.RecursoNaoEncontradoException;
import com.gabrielx47.request_manager_api.repository.SolicitacaoRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SolicitacaoService {
    private SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public PagedModel<SolicitacaoListagemDTO> listarParteDasSolicitacoes(String status, LocalDate dataInicio, LocalDate dataFim, String categoria, Pageable pageable) {
        Page<SolicitacaoListagemDTO> solicitacoes;
        if(status == null && dataInicio == null && dataFim == null && categoria == null) {
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesSemFiltro(pageable);
        } else if (status != null && categoria != null && dataInicio != null && dataFim != null) {
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
            solicitacoes = solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosStatusPeriodoCategoria(status, dataInicio, dataFim, categoria, pageable);
        }

        return new PagedModel<SolicitacaoListagemDTO>(solicitacoes);
    }

    public SolicitacaoCompletaDTO encontrarTodosOsDadosDaSolicitacao(Long id) {
        return solicitacaoRepository.selecionarTodosDadosDaSolicitacaoPorId(id)
        .orElseThrow(() -> new RecursoNaoEncontradoException("Solicitação não encontrada"));    
    }

    public String atualizarStatusDaSolicitacao(Long id, String status) {
        solicitacaoRepository.atualizarStatusDaSolicitacao(id, status);
        return "Status da solicitcação atualizado com sucesso";
    }
}
