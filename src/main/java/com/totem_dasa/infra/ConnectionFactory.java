package com.totem_dasa.infra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
  private final String URL = "jdbc:postgresql://localhost:5432/totem_dasa";
  private final String USER = "totem_user";
  private final String PASSWORD = "totem_password";

  public Connection getConnection() {
    try {
      return DriverManager.getConnection(URL, USER, PASSWORD);
    } catch (SQLException e) { throw new RuntimeException(e); }
  }
}