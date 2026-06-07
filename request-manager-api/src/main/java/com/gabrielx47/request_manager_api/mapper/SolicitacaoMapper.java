package com.gabrielx47.request_manager_api.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.projection.SolicitacaoCompletaProjection;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SolicitacaoMapper {
    SolicitacaoCompletaDTO mapearSolicitacaoCompletaProjectionParaSolicitacaoCompletaDTO(SolicitacaoCompletaProjection solicitacao);
}
