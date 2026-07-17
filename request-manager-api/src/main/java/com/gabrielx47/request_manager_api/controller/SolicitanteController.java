package com.gabrielx47.request_manager_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielx47.request_manager_api.dto.NovoSolicitanteDTO;
import com.gabrielx47.request_manager_api.dto.SolicitanteDTO;
import com.gabrielx47.request_manager_api.service.SolicitanteService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/solicitantes")
public class SolicitanteController {
    private final SolicitanteService solicitanteService;

    public SolicitanteController(SolicitanteService solicitanteService) {
        this.solicitanteService = solicitanteService;
    }

    @PostMapping()
    public ResponseEntity<String> criarUmNovoSolicitante(@Valid @RequestBody NovoSolicitanteDTO dados) {
        return ResponseEntity.ok().body(solicitanteService.criarSolicitante(dados));  
    }

    @GetMapping()
    public ResponseEntity<List<SolicitanteDTO>> obterCategorias() {
        return ResponseEntity.ok().body(solicitanteService.encontrarOsDadosDeTodosSolicitantes());
    }
}
