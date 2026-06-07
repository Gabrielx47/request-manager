package com.gabrielx47.request_manager_api.projection;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.gabrielx47.request_manager_api.model.entity.Status;

public interface SolicitacaoCompletaProjection {
    String getDescricao();
    BigDecimal getValor();
    LocalDate getDataSolicitacao();
    Status getStatus();
    String getNomeDaCategoria();
    String getNomeDoSolicitante();
    String getCpfCnpj();
}
