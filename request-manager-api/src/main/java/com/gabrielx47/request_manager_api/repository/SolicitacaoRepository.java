package com.gabrielx47.request_manager_api.repository;

import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.model.entity.Solicitacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {
    @Query(value = "SELECT l.nome AS nomeDoSolicitante, l.cpf_cnpj, c.nome AS nomeDaCategoria, s.status, s.valor " +
                        "FROM tb_solicitacao AS s " +
                        "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                        "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id",
            nativeQuery = true)
    Page<SolicitacaoListagemDTO> selecionarParteDasSolicitacoes(Pageable pageable);

    @NativeQuery(value = "SELECT s.descricao, s.valor, s.data_solicitacao, s.status, c.nome AS nomeDaCategoria, l.nome AS nomeDoSolicitante, l.cpf_cnpj " +
                        "FROM tb_solicitacao AS s " +
                        "INNER JOIN tb_categoria AS c ON s.categoria_id = c.id " +
                        "INNER JOIN tb_solicitante AS l ON s.solicitante_id = l.id " +
                        "WHERE s.id = :id")
    SolicitacaoCompletaDTO selecionarTodosDadosDaSolicitacaoPorId(Long id);
}