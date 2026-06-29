package com.gabrielx47.request_manager_api.model.entity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StatusTests {
    @Test
    public void transicaoValidaDeSolicitadoParaLiberadoTest() {
        // Arrange
        Status statusAtual = Status.SOLICITADO;
        String novoStatus = "LIBERADO";

        // Act
        boolean resultado = statusAtual.transicaoValida(novoStatus);

        // Assert
        assertTrue(resultado, "Transição de status SOLICITADO para LIBERADO deve ser válida");
    }

    @Test
    public void transicaoValidaDeSolicitadoParaRejeitadoTest() {
        Status statusAtual = Status.SOLICITADO;
        String novoStatus = "REJEITADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertTrue(resultado, "Transição de status SOLICITADO para REJEITADO deve ser válida");
    }

    @Test
    public void transicaoInvalidaDeSolicitadoParaCanceladoTest() {
        Status statusAtual = Status.SOLICITADO;
        String novoStatus = "CANCELADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertThat(resultado).isFalse();
    }

    @Test
    public void transicaoValidaDeLiberadoParaAprovadoTest() {
        Status statusAtual = Status.LIBERADO;
        String novoStatus = "APROVADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertTrue(resultado, "Transição de status LIBERADO para APROVADO deve ser válida");
    }

    @Test
    public void transicaoValidaDeLiberadoParaRejeitadoTest() {
        Status statusAtual = Status.LIBERADO;
        String novoStatus = "REJEITADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertTrue(resultado, "Transição de status LIBERADO para REJEITADO deve ser válida");
    }

    @Test
    public void transicaoInvalidaDeLiberadoParaCanceladoTest() {
        Status statusAtual = Status.LIBERADO;
        String novoStatus = "CANCELADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertThat(resultado).isFalse();
    }

    @Test
    public void transicaoValidaDeAprovadoParaCanceladoTest() {
        Status statusAtual = Status.APROVADO;
        String novoStatus = "CANCELADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertThat(resultado).isTrue();
    }

    @Test
    public void transicaoInvalidaDeAprovadoParaLiberadoTest() {
        Status statusAtual = Status.APROVADO;
        String novoStatus = "LIBERADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertThat(resultado).isFalse();
    }

    @Test
    public void transicaoInvalidaDeRejeitadoParaLiberadoTest() {
        Status statusAtual = Status.REJEITADO;
        String novoStatus = "LIBERADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertThat(resultado).isFalse();
    }

    @Test
    public void transicaoInvalidaDeCanceladoParaAprovadoTest() {
        Status statusAtual = Status.CANCELADO;
        String novoStatus = "APROVADO";

        boolean resultado = statusAtual.transicaoValida(novoStatus);

        assertThat(resultado).isFalse();
    }
}
