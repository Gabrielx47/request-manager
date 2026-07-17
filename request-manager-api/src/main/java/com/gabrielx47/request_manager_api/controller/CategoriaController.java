package com.gabrielx47.request_manager_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gabrielx47.request_manager_api.dto.CategoriaDTO;
import com.gabrielx47.request_manager_api.dto.NovaCategoriaDTO;
import com.gabrielx47.request_manager_api.service.CategoriaService;

import jakarta.validation.Valid;



@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping()
    public ResponseEntity<String> criarUmaNovaCategoria(@Valid @RequestBody NovaCategoriaDTO nome) {
        return ResponseEntity.ok().body(categoriaService.criarCategoria(nome.getNome()));
    }

    @GetMapping()
    public ResponseEntity<List<CategoriaDTO>> obterCategorias() {
        return ResponseEntity.ok().body(categoriaService.encontrarOsNomesDasCategorias());
    }
    
    
}
