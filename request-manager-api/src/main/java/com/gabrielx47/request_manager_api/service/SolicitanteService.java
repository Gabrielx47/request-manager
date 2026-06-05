package com.gabrielx47.request_manager_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gabrielx47.request_manager_api.dto.NovoSolicitanteDTO;
import com.gabrielx47.request_manager_api.dto.SolicitanteDTO;
import com.gabrielx47.request_manager_api.mapper.SolicitanteMapper;
import com.gabrielx47.request_manager_api.model.entity.Solicitante;
import com.gabrielx47.request_manager_api.repository.SolicitanteRepository;

@Service
public class SolicitanteService {
    SolicitanteRepository solicitanteRepository;
    SolicitanteMapper solicitanteMapper;

    public SolicitanteService(SolicitanteRepository solicitanteRepository, SolicitanteMapper solicitanteMapper) {
        this.solicitanteRepository = solicitanteRepository;
        this.solicitanteMapper = solicitanteMapper;
    }

    public String criarSolicitante(NovoSolicitanteDTO novoSolicitanteDTO) {
        solicitanteRepository.inserirSolicitante(novoSolicitanteDTO.getNome(), novoSolicitanteDTO.getCpfCnpj());
        return "Solicitante criado com sucesso";
    }

    public List<SolicitanteDTO> encontrarOsDadosDeTodosSolicitantes() {
        List<Solicitante> solicitantes = solicitanteRepository.selecionarTodosOsdadosDosSolicitantes();
        return solicitanteMapper.mapearSolicitanteParaSolicitanteDTO(solicitantes);
    }
}
