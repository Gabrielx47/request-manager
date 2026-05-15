package com.gabrielx47.request_manager_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.transaction.annotation.Transactional;

import com.gabrielx47.request_manager_api.dto.CategoriaDTO;
import com.gabrielx47.request_manager_api.model.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>    {
    @Modifying
    @Transactional
    @NativeQuery(value = "INSERT INTO tb_categoria (nome) VALUES (:nome)")
    void inserirCategoria(String nome);

    @NativeQuery(value = "SELECT * FROM tb_categoria")
    List<CategoriaDTO> selecionarTodosOsNomesDasCategorias();
}
