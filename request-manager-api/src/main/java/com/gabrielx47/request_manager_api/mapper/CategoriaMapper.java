package com.gabrielx47.request_manager_api.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.InjectionStrategy;

import com.gabrielx47.request_manager_api.dto.CategoriaDTO;
import com.gabrielx47.request_manager_api.model.entity.Categoria;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CategoriaMapper {
    List<CategoriaDTO> mapearCategoriaParaCategoriaDTO(List<Categoria> categorias);
}
