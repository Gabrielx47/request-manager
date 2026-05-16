package com.gabrielx47.request_manager_api.dto;


public class SolicitanteDTO {
    private Long id;
    private String cpfCnpj;
    private String nome;
    
    
    public SolicitanteDTO() {
    }

    public SolicitanteDTO(Long id,  String cpfCnpj, String nome) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }
}
