package com.gabrielx47.request_manager_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gabrielx47.request_manager_api.dto.CategoriaDTO;
import com.gabrielx47.request_manager_api.mapper.CategoriaMapper;
import com.gabrielx47.request_manager_api.model.entity.Categoria;
import com.gabrielx47.request_manager_api.repository.CategoriaRepository;
import com.gabrielx47.request_manager_api.mapper.CategoriaMapper;

@Service
public class CategoriaService {
    private CategoriaRepository categoriaRepository;
    private CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public String criarCategoria(String nome) {
        categoriaRepository.inserirCategoria(nome);
        return "Categoria criada com sucesso";
    }

    public List<CategoriaDTO> encontrarOsNomesDasCategorias() {
        List<Categoria> categorias = categoriaRepository.selecionarTodosOsNomesDasCategorias();
        return categoriaMapper.mapearCategoriaParaCategoriaDTO(categorias);
    }
}
