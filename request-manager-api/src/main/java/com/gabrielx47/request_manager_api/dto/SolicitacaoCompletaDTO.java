package com.gabrielx47.request_manager_api.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class SolicitacaoCompletaDTO {
    private String descricao;
    private BigDecimal valor;
    private LocalDate dataSolicitacao;
    private String status;
    private String nomeDaCategoria;
    private String nomeDoSolicitante;
    private String cpfCnpj;

    public SolicitacaoCompletaDTO() {
    }

    public SolicitacaoCompletaDTO(String descricao, BigDecimal valor, LocalDate dataSolicitacao, String status,
            String nomeDaCategoria, String nomeDoSolicitante, String cpfCnpj) {
        this.descricao = descricao;
        this.valor = valor;
        this.dataSolicitacao = dataSolicitacao;
        this.status = status;
        this.nomeDaCategoria = nomeDaCategoria;
        this.nomeDoSolicitante = nomeDoSolicitante;
        this.cpfCnpj = cpfCnpj;
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

    public String getNomeDaCategoria() {
        return nomeDaCategoria;
    }

    public void setNomeDaCategoria(String nomeDaCategoria) {
        this.nomeDaCategoria = nomeDaCategoria;
    }

    public String getNomeDoSolicitante() {
        return nomeDoSolicitante;
    }

    public void setNomeDoSolicitante(String nomeDoSolicitante) {
        this.nomeDoSolicitante = nomeDoSolicitante;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}
