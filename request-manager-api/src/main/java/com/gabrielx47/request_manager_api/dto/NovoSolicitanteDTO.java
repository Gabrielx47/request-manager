package com.gabrielx47.request_manager_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class NovoSolicitanteDTO {
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O CPF ou CNPJ é obrigatório")
    @Size(min = 11, max = 14, message = "O CPF e o CNPJ devem conter, respectivamente, 11 e 14 caracteres")
    private String cpfCnpj;

    public NovoSolicitanteDTO(String nome, String cpfCnpj) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
    }

    public String getNome() {
        return nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }
}
