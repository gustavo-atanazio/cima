package com.cima.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  private final String URL = "jdbc:postgresql://localhost:5432/cima";
  private final String USER = "cima_user";
  private final String PASSWORD = "cima_password";

  public Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) { throw new RuntimeException(e); }
  }
}