package com.gabrielx47.request_manager_api.controller;

import java.time.LocalDate;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielx47.request_manager_api.dto.NovaSolicitacaoDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoStatusDTO;
import com.gabrielx47.request_manager_api.service.SolicitacaoService;

import jakarta.validation.Valid;




@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @GetMapping
    ResponseEntity<PagedModel<SolicitacaoListagemDTO>> obterListagemDeSolicitacoes(
            @RequestParam(required = false) String status, 
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio, 
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal, 
            @RequestParam(required = false) String categoria, @RequestParam(defaultValue = "0") int numeroDaPagina, 
        @RequestParam(defaultValue = "10") int numeroDeElementosPorPagina) {
        
        PagedModel<SolicitacaoListagemDTO> solicitacoes = solicitacaoService.listarParteDasSolicitacoes(status, dataInicio, dataFinal, categoria, PageRequest.of(numeroDaPagina, numeroDeElementosPorPagina));

        System.out.println(solicitacoes);
        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping("/{id}")
    ResponseEntity<SolicitacaoCompletaDTO> obterTodosDadosDeUmaSolicitacao(@PathVariable Long id) {
        SolicitacaoCompletaDTO solicitacao = solicitacaoService.encontrarTodosOsDadosDaSolicitacao(id);
        return ResponseEntity.ok(solicitacao);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> atualizarStatusDaSolicitacao(@PathVariable Long id, @RequestBody @Valid SolicitacaoStatusDTO status) {
        return ResponseEntity.ok(solicitacaoService.atualizarStatusDaSolicitacao(id, status.getStatus()));
    }
    
    @PostMapping()
    public ResponseEntity<String> criarNovaSolicitacao(@Valid @RequestBody NovaSolicitacaoDTO novaSolicitacao) {
        return ResponseEntity.ok(solicitacaoService.salvarNovaSolicitacao(novaSolicitacao));
    }
    
}
