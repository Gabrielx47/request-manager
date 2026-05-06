package com.gabrielx47.request_manager_api.model.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_solicitacao")
public class Solicitacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;
    @Column(name = "descricao", length = 200, nullable = false)
    private String descricao;
    @Column(name = "valor", scale = 2 ,nullable = false)
    private BigDecimal valor;
    @Column(name = "data_solicitacao", nullable = false)
    private LocalDate dataSolicitacao;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne()
    @JoinColumn(name = "solicitante_id")
    private Solicitante solicitante;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Solicitante getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Solicitante solicitante) {
        this.solicitante = solicitante;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Solicitacao that = (Solicitacao) o;
        return Objects.equals(id, that.id) && Objects.equals(descricao, that.descricao) && Objects.equals(valor, that.valor) && Objects.equals(dataSolicitacao, that.dataSolicitacao) && status == that.status && Objects.equals(solicitante, that.solicitante) && Objects.equals(categoria, that.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, valor, dataSolicitacao, status, solicitante, categoria);
    }
}
