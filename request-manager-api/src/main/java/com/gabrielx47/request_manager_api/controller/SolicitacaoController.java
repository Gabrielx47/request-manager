package com.gabrielx47.request_manager_api.controller;

import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.service.SolicitacaoService;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {
    SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @GetMapping
    ResponseEntity<List<SolicitacaoListagemDTO>> obterListagemDeSolicitacoes(@RequestParam int numeroDaPagina, 
        @RequestParam int numeroDeElementosPorPagina) {
        List<SolicitacaoListagemDTO> solicitacoes = solicitacaoService.encontrarTodosOsDadosDaSolicitacao(PageRequest.of(numeroDaPagina, numeroDeElementosPorPagina));
        System.out.println(solicitacoes);
        return ResponseEntity.ok(solicitacoes);
    }

    @GetMapping("/{id}")
    ResponseEntity<SolicitacaoCompletaDTO> obterTodosDadosDeUmaSolicitacao(@PathVariable Long id) {
        SolicitacaoCompletaDTO solicitacao = solicitacaoService.encontrarTodosOsDadosDaSolicitacao(id);
        return ResponseEntity.ok(solicitacao);
    }
}
