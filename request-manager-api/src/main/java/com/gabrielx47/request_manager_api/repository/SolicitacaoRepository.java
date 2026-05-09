package com.gabrielx47.request_manager_api.repository;

import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.model.entity.Solicitacao;

import jakarta.transaction.Transactional;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                        "FROM tb_solicitacao AS s " +
                        "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                        "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id ",
            nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoesSemFiltro(Pageable pageable);

    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
            "FROM tb_solicitacao AS s " +
            "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
            "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
            "WHERE s.status = :status AND (s.data_solicitacao BETWEEN :dataInicio AND :dataFim) AND (c.nome = :categoria)",
            nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoesComFiltrosStatusPeriodoCategoria(String status, LocalDate dataInicio, LocalDate dataFim, String categoria , Pageable pageable);

    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.status = :status AND (s.data_solicitacao BETWEEN :dataInicio AND :dataFim)", nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoesComFiltrosStatusPeriodo(String status,
                    LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.status = :status AND c.nome = :categoria", nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoesComFiltrosStatusCategoria(String status,
                    String categoria, Pageable pageable);

    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.status = :status AND (s.data_solicitacao BETWEEN :dataInicio AND :dataFim)", nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoesComFiltrosCategoriaPeriodo(String categoria,
                    LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
    
    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.status = :status", nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoesComFiltroStatus(String status,
                    Pageable pageable);                    
    
    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.data_solicitacao BETWEEN :dataInicio AND :dataFim", nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoesComFiltroPeriodo(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
   
    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE c.nome = :categoria", nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoesComFiltroCategoria(String categoria, Pageable pageable);

    @NativeQuery(value = "SELECT s.descricao, s.valor, s.data_solicitacao, s.status, c.nome AS nomeDaCategoria, l.nome AS nomeDoSolicitante, l.cpf_cnpj " +
                        "FROM tb_solicitacao AS s " +
                        "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                        "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                        "WHERE s.id = :id")
    SolicitacaoCompletaDTO selecionarTodosDadosDaSolicitacaoPorId(Long id);

    @Modifying
    @Transactional
    @NativeQuery(value = "UPDATE tb_solicitacao SET status = :status WHERE id = :id")
    void atualizarStatusDaSolicitacao(Long id, String status);
}