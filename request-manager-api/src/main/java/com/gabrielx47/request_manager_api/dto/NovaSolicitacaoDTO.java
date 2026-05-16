package com.gabrielx47.request_manager_api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class NovaSolicitacaoDTO {
    @Size(min = 0, max=200, message = "A descrição deve ter no máximo 200 caracteres")
    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;
    @Min(value = 0, message = "O valor deve ser maior ou igual a zero")
    @NotNull(message = "O valor do pagamento é obrigatório")
    private BigDecimal valor;
    @NotNull(message = "A data da solicitação é obrigatória")
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataSolicitacao;
    @NotNull(message = "O status da solicitação é obrigatório")
    @Pattern(regexp = "^SOLICITADO", message = "É obrigatório que o status seja SOLICITADO")
    private String status;
    @NotNull(message = "A categoria é obrigatória")
    private Long categoria_id;
    @NotNull(message = "O solicitante é obrigatório")
    private Long solicitante_id;

    public NovaSolicitacaoDTO() {
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
    public Long getCategoria_id() {
        return categoria_id;
    }

    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    public Long getSolicitante_id() {
        return solicitante_id;
    }

    public void setSolicitante_id(Long solicitante_id) {
        this.solicitante_id = solicitante_id;
    }
}
