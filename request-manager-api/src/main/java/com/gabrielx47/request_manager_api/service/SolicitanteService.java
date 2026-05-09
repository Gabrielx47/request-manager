package com.gabrielx47.request_manager_api.service;

import org.springframework.stereotype.Service;

import com.gabrielx47.request_manager_api.dto.NovoSolicitanteDTO;
import com.gabrielx47.request_manager_api.repository.SolicitanteRepository;

@Service
public class SolicitanteService {
    SolicitanteRepository solicitanteRepository;

    public SolicitanteService(SolicitanteRepository solicitanteRepository) {
        this.solicitanteRepository = solicitanteRepository;
    }

    public String criarSolicitante(NovoSolicitanteDTO novoSolicitanteDTO) {
        solicitanteRepository.inserirSolicitante(novoSolicitanteDTO.getNome(), novoSolicitanteDTO.getCpfCnpj());
        return "Solicitante criado com sucesso";
    }
}
