package com.cima.errors;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ErrorHandler {
  private record ErrorResponse(
    LocalDateTime timestamp,
    int status,
    String error,
    String message
  ) {}

  private ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String error, String message) {
    ErrorResponse body = new ErrorResponse(
      LocalDateTime.now(),
      status.value(),
      error,
      message
    );

    return ResponseEntity.status(status).body(body);
  }

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException exception) {
    return buildResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado", exception.getMessage());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleDataIntegrityViolation() {
    return buildResponse(
      HttpStatus.BAD_REQUEST,
      "Violação de integridade referencial", 
      "O recurso está sendo referenciado por outras tabelas e não pode ser excluído."
    );
  }

  @ExceptionHandler(BusinessRuleException.class)
  public ResponseEntity<ErrorResponse> handleBusinessRule(BusinessRuleException exception) {
    return buildResponse(
      HttpStatus.BAD_REQUEST,
      "Violação de regra de negócio", 
      exception.getMessage()
    );
  }

  @ExceptionHandler(InvalidReferenceException.class)
  public ResponseEntity<ErrorResponse> handleInvalidReference(InvalidReferenceException exception) {
    return buildResponse(
      HttpStatus.BAD_REQUEST,
      "Referência inválida",
      exception.getMessage()
    );
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException exception) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", exception.getMessage());
  }
}