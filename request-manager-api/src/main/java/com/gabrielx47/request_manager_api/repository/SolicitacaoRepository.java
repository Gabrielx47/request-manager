package com.gabrielx47.request_manager_api.repository;

import com.gabrielx47.request_manager_api.dto.SolicitacaoDTO;
import com.gabrielx47.request_manager_api.model.entity.Solicitacao;
import com.gabrielx47.request_manager_api.projection.SolicitacaoCompletaProjection;
import com.gabrielx47.request_manager_api.projection.SolicitacaoListagemProjection;

import jakarta.transaction.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    @Query(value = "SELECT s.id, l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                        "FROM tb_solicitacao AS s " +
                        "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                        "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id ",
            nativeQuery = true)
    Page<SolicitacaoListagemProjection> selecionarParteDasSolicitacoesSemFiltro(Pageable pageable);

    @Query(value = "SELECT s.id, l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
            "FROM tb_solicitacao AS s " +
            "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
            "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
            "WHERE s.status = :status AND (s.data_solicitacao BETWEEN :dataInicio AND :dataFim) AND (c.nome = :categoria)",
            nativeQuery = true)
    Page<SolicitacaoListagemProjection> selecionarParteDasSolicitacoesComFiltrosStatusPeriodoCategoria(String status, LocalDate dataInicio, LocalDate dataFim, String categoria , Pageable pageable);

    @Query(value = "SELECT s.id, l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.status = :status AND (s.data_solicitacao BETWEEN :dataInicio AND :dataFim)", nativeQuery = true)
    Page<SolicitacaoListagemProjection> selecionarParteDasSolicitacoesComFiltrosStatusPeriodo(String status,
                    LocalDate dataInicio, LocalDate dataFim, Pageable pageable);

    @Query(value = "SELECT s.id, l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.status = :status AND c.nome = :categoria", nativeQuery = true)
    Page<SolicitacaoListagemProjection> selecionarParteDasSolicitacoesComFiltrosStatusCategoria(String status,
                    String categoria, Pageable pageable);

    @Query(value = "SELECT s.id, l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.status = :status AND (s.data_solicitacao BETWEEN :dataInicio AND :dataFim)", nativeQuery = true)
    Page<SolicitacaoListagemProjection> selecionarParteDasSolicitacoesComFiltrosCategoriaPeriodo(String categoria,
                    LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
    
    @Query(value = "SELECT s.id, l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.status = :status", nativeQuery = true)
    Page<SolicitacaoListagemProjection> selecionarParteDasSolicitacoesComFiltroStatus(String status,
                    Pageable pageable);                    
    
    @Query(value = "SELECT s.id, l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE s.data_solicitacao BETWEEN :dataInicio AND :dataFim", nativeQuery = true)
    Page<SolicitacaoListagemProjection> selecionarParteDasSolicitacoesComFiltroPeriodo(LocalDate dataInicio, LocalDate dataFim, Pageable pageable);
   
    @Query(value = "SELECT s.id, l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                    "FROM tb_solicitacao AS s " +
                    "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                    "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                    "WHERE c.nome = :categoria", nativeQuery = true)
    Page<SolicitacaoListagemProjection> selecionarParteDasSolicitacoesComFiltroCategoria(String categoria, Pageable pageable);

    @NativeQuery(value = "SELECT s.descricao, s.valor, s.data_solicitacao, s.status, c.nome AS nomeDaCategoria, l.nome AS nomeDoSolicitante, l.cpf_cnpj " +
                        "FROM tb_solicitacao AS s " +
                        "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                        "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                        "WHERE s.id = :id")
    Optional<SolicitacaoCompletaProjection> selecionarTodosDadosDaSolicitacaoPorId(Long id);

    @NativeQuery(value = "SELECT id, descricao, valor, data_solicitacao, status, solicitante_id, categoria_id FROM tb_solicitacao WHERE id = :id")
    Optional<Solicitacao> encontrarSolicitacaoPorId(Long id);

    @Modifying
    @Transactional
    @NativeQuery(value = "UPDATE tb_solicitacao SET status = :status WHERE id = :id")
    void atualizarStatusDaSolicitacao(Long id, String status);

    @Modifying
    @Transactional
    @NativeQuery(value = "INSERT INTO tb_solicitacao (descricao, valor, data_solicitacao, status, solicitante_id, categoria_id) "+ 
                        "VALUES (:#{#solicitacao.getDescricao()}, :#{#solicitacao.getValor()}, :#{#solicitacao.getDataSolicitacao()}, :#{#solicitacao.getStatus().name()}, :#{#solicitacao.getSolicitante().getId()}, :#{#solicitacao.getCategoria().getId()})")    
    void inserirNovaSolicitacao(@Param("solicitacao") Solicitacao solicitacao);

}