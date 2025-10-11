package com.cima.errors;

public class BusinessRuleException extends RuntimeException {
  public BusinessRuleException(String message) { super(message); }
}