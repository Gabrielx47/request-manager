package com.gabrielx47.request_manager_api.service;

import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.repository.SolicitacaoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SolicitacaoService {
    private SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public List<SolicitacaoListagemDTO> listarParteDasSolicitacoes(String status, LocalDate dataInicio, LocalDate dataFim, String categoria, Pageable pageable) {
        if(status == null && dataInicio == null && dataFim == null && categoria == null) {
            return solicitacaoRepository.selecionarParteDasSolicitacoesSemFiltro(pageable).getContent();
        } else if (status != null && categoria != null && dataInicio != null && dataFim != null) {
            return solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosStatusPeriodoCategoria(status, dataInicio, dataFim, categoria, pageable).getContent();
        } else if (status != null && dataInicio != null && dataFim != null) {
            return solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosStatusPeriodo(status, dataInicio, dataFim, pageable).getContent();
        } else if (status != null && categoria != null) { 
            return solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosStatusCategoria(status, categoria, pageable).getContent();
        } else if (categoria != null && dataInicio != null && dataFim != null) {
            return solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosCategoriaPeriodo(categoria, dataInicio, dataFim, pageable).getContent();
        } else if (status != null) {
            return solicitacaoRepository.selecionarParteDasSolicitacoesComFiltroStatus(status, pageable).getContent();
        } else if (dataInicio != null && dataFim != null) {
            return solicitacaoRepository.selecionarParteDasSolicitacoesComFiltroPeriodo(dataInicio, dataFim, pageable).getContent();
        } else if (categoria != null) {
            return solicitacaoRepository.selecionarParteDasSolicitacoesComFiltroCategoria(categoria, pageable).getContent();
        } else {
            return solicitacaoRepository.selecionarParteDasSolicitacoesComFiltrosStatusPeriodoCategoria(status, dataInicio, dataFim, categoria, pageable).getContent();
        }
    }

    public SolicitacaoCompletaDTO encontrarTodosOsDadosDaSolicitacao(Long id) {
        return solicitacaoRepository.selecionarTodosDadosDaSolicitacaoPorId(id);
    }

    public String atualizarStatusDaSolicitacao(Long id, String status) {
        solicitacaoRepository.atualizarStatusDaSolicitacao(id, status);
        return "Status da solicitcação atualizado com sucesso";
    }
}
