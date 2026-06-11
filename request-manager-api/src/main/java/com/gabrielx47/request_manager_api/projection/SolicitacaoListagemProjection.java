package com.gabrielx47.request_manager_api.projection;

import java.math.BigDecimal;

public interface SolicitacaoListagemProjection {
    Long getId();
    String getNomeDoSolicitante();
    String getCpfCnpj();
    String getNomeDaCategoria();
    String getStatus();
    BigDecimal getValor();
}
