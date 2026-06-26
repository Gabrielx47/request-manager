package com.gabrielx47.request_manager_api.model.entity;

public enum Status {
    SOLICITADO("SOLICITADO", new String[]{"LIBERADO", "REJEITADO"}),
    LIBERADO("LIBERADO", new String[]{"APROVADO", "REJEITADO"}),
    APROVADO("APROVADO", new String[]{"CANCELADO"}), 
    REJEITADO("REJEITADO", new String[]{}), 
    CANCELADO("CANCELADO", new String[]{});

    private final String nome;
    private final String[] transicoesValidas; 

    Status(String nome,String[] transicoesValidas) {
        this.nome = nome;
        this.transicoesValidas = transicoesValidas;
    }

    public String getNome() {
        return nome;
    }

    public boolean transicaoValida(String novoStatus) {
        for (String transicao : transicoesValidas) {
            if (transicao.equals(novoStatus)) {
                return true;
            }
        }
        return false;
    }
};
