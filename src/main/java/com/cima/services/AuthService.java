package com.cima.services;

public class AuthService {
  private final String TOKEN = "valid-token";

  public void auth(String token) {
    if (!TOKEN.equals(token)) throw new SecurityException("Token inválido.");
  }
}