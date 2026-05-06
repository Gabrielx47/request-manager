package com.gabrielx47.request_manager_api.dto;

import com.gabrielx47.request_manager_api.model.entity.Status;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SolicitacaoCompletaDTO {
    private String descricao;
    private BigDecimal valor;
    private LocalDate dataSolicitacao;
    private Status status;
    private String nomeDaCategoria;
    private String nomeDoSolicitante;
    private String cpfCnpj;

    public SolicitacaoCompletaDTO() {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNomeDaCategoria() {
        return nomeDaCategoria;
    }

    public void setNomeDaCategoria(String nomeDaCategoria) {
        this.nomeDaCategoria = nomeDaCategoria;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}
