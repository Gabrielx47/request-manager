package com.gabrielx47.request_manager_api.dto;


import java.math.BigDecimal;

public class SolicitacaoListagemDTO {
    private Long id;
    private String nomeDoSolicitante;
    private String cpfCnpj;
    private String nomeDaCategoria;
    private String status;
    private BigDecimal valor;

    public SolicitacaoListagemDTO() {
    }

    

    public SolicitacaoListagemDTO(Long id, String nomeDoSolicitante, String cpfCnpj, String nomeDaCategoria, String status,
            BigDecimal valor) {
        this.id = id;
        this.nomeDoSolicitante = nomeDoSolicitante;
        this.cpfCnpj = cpfCnpj;
        this.nomeDaCategoria = nomeDaCategoria;
        this.status = status;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNomeDaCategoria() {
        return nomeDaCategoria;
    }

    public void setNomeDaCategoria(String nomeDaCategoria) {
        this.nomeDaCategoria = nomeDaCategoria;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
