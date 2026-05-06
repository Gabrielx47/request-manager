package com.gabrielx47.request_manager_api.service;

import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.repository.SolicitacaoRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoService {
    private SolicitacaoRepository solicitacaoRepository;

    public SolicitacaoService(SolicitacaoRepository solicitacaoRepository) {
        this.solicitacaoRepository = solicitacaoRepository;
    }

    public List<SolicitacaoListagemDTO> listarParteDasSolicitacoes(Pageable pageable) {
        return solicitacaoRepository.selecionarParteDasSolicitacoes(pageable).getContent();
    }

    public SolicitacaoCompletaDTO encontrarTodosOsDadosDaSolicitacao(Long id) {
        return solicitacaoRepository.selecionarTodosDadosDaSolicitacaoPorId(id);
    }
}
