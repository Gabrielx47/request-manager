package com.gabrielx47.request_manager_api.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ManipuladorDeExcecoesController {

    ManipuladorDeExcecoesController() {
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ProblemDetail> tratarRecursoNaoEncontradoException(RecursoNaoEncontradoException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(TransicaoDeStatusDaInvalidaException.class)
    public ResponseEntity<ProblemDetail> tratarTransicaoDeStatusDaInvalidaException(TransicaoDeStatusDaInvalidaException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> tratarDadosInvalidosException(MethodArgumentNotValidException e) {
        Map<String, String> erros = e.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap(
                        error -> error.getField(),
                        error -> error.getDefaultMessage()
                ));

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Dados inválidos");
        problemDetail.setProperty("errors", erros);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(DataFinalNulaException.class)
    public ResponseEntity<ProblemDetail> tratarDataFinalNulaException(DataFinalNulaException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Data Vazia");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    @ExceptionHandler(DataFinalAnteriorADataInicialException.class)
    public ResponseEntity<ProblemDetail> tratarDataFinalMenorQueDataInicialException(DataFinalAnteriorADataInicialException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Data Final Anterior à Data Inicial");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);   
    }
}
