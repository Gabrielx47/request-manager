package com.gabrielx47.request_manager_api.dto;

import jakarta.validation.constraints.NotBlank;

public class NovaCategoriaDTO {
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    public NovaCategoriaDTO() {
    }

    public NovaCategoriaDTO(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
