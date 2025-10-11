package com.cima.errors;

public class InvalidReferenceException extends RuntimeException {
  public InvalidReferenceException(String message) { super(message); }
}