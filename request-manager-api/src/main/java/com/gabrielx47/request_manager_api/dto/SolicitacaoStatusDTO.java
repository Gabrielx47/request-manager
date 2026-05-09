package com.gabrielx47.request_manager_api.dto;

import jakarta.validation.constraints.Pattern;

public class SolicitacaoStatusDTO {
    @Pattern(regexp = "SOLICITADO|LIBERADO|REJEITADO|APROVADO|CANCELADO", message = "Status deve ser SOLICITADO, LIBERADO, REJEITADO, APROVADO ou CANCELADO")
    private String status;

    public SolicitacaoStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
