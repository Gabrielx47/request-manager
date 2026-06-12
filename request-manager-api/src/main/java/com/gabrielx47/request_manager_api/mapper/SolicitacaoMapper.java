package com.gabrielx47.request_manager_api.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import com.gabrielx47.request_manager_api.dto.NovaSolicitacaoDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoCompletaDTO;
import com.gabrielx47.request_manager_api.dto.SolicitacaoListagemDTO;
import com.gabrielx47.request_manager_api.model.entity.Solicitacao;
import com.gabrielx47.request_manager_api.projection.SolicitacaoCompletaProjection;
import com.gabrielx47.request_manager_api.projection.SolicitacaoListagemProjection;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SolicitacaoMapper {
    SolicitacaoCompletaDTO mapearSolicitacaoCompletaProjectionParaSolicitacaoCompletaDTO(SolicitacaoCompletaProjection solicitacao);
    SolicitacaoListagemDTO mapearSolicitacaoListagemProjectionParaSolicitacaoListagemDTO(SolicitacaoListagemProjection solicitacao);
    default Page<SolicitacaoListagemDTO> mapearPageSolicitacaoListagemProjectionParaSolicitacaoListagemDTO(Page<SolicitacaoListagemProjection> solicitacao) {
        return solicitacao.map(s ->  mapearSolicitacaoListagemProjectionParaSolicitacaoListagemDTO(s));
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "solicitante.id", source = "solicitante_id")
    @Mapping(target = "categoria.id", source = "categoria_id")
    Solicitacao mapearNovaSolicitacaoDTOParaSolicitacao(NovaSolicitacaoDTO novaSolicitacao);
}
