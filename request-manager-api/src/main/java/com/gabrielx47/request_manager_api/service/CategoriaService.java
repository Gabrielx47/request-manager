package com.gabrielx47.request_manager_api.service;

import org.springframework.stereotype.Service;

import com.gabrielx47.request_manager_api.repository.CategoriaRepository;

@Service
public class CategoriaService {
    CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public String criarCategoria(String nome) {
        categoriaRepository.inserirCategoria(nome);
        return "Categoria criada com sucesso";
    }

}
