package com.gabrielx47.request_manager_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gabrielx47.request_manager_api.model.entity.Solicitante;

import jakarta.transaction.Transactional;

public interface SolicitanteRepository extends JpaRepository<Solicitante, Long>     {
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tb_solicitante (nome, cpf_cnpj) VALUES (:nome, :cpfCnpj)", nativeQuery = true)
    void inserirSolicitante(String nome, String cpfCnpj);
}
