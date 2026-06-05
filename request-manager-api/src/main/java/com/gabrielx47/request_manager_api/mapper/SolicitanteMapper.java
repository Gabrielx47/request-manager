package com.gabrielx47.request_manager_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.InjectionStrategy;

import com.gabrielx47.request_manager_api.dto.SolicitanteDTO;
import com.gabrielx47.request_manager_api.model.entity.Solicitante;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SolicitanteMapper  {
    List<SolicitanteDTO> mapearSolicitanteParaSolicitanteDTO(List<Solicitante> solicitantes);
}
