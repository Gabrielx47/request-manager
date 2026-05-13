package com.gabrielx47.request_manager_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SolicitacaoDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate dataSolicitacao;
    private String status;
   
    public SolicitacaoDTO() {
    }

    public SolicitacaoDTO(Long id, String descricao, BigDecimal valor, LocalDate dataSolicitacao, String status) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
