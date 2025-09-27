package com.cima.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cima.infra.ConnectionFactory;
import com.cima.models.Totem;

public class TotemDAO implements CRUD<Totem, Integer> {
  private final String TABLE_NAME = "totem";
  private final String ID_COLUMN = "id";
  private final String UNIT_ID_COLUMN = "id_unidade";
  private final Connection connection = new ConnectionFactory().getConnection();

  @Override
  public List<Totem> findAll() {
    final String sql = "SELECT * FROM " + TABLE_NAME;
    List<Totem> totems = new ArrayList<>();
    
    try (
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery()
    ) {
      while (rs.next()) {
        totems.add(new Totem(
          rs.getInt(ID_COLUMN),
          rs.getInt(UNIT_ID_COLUMN)
        ));
      }
    } catch (SQLException e) { throw new RuntimeException(e); }

    return totems;
  }

  @Override
  public Optional<Totem> findById(Integer id) {
    final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(new Totem(
          rs.getInt(ID_COLUMN),
          rs.getInt(UNIT_ID_COLUMN)
        ));
      }
    } catch (SQLException e) { throw new RuntimeException(e); }

    return Optional.empty();
  }

  @Override
  public Totem create(Totem entity) {
    final String sql = "INSERT INTO " + TABLE_NAME + " (" + UNIT_ID_COLUMN + ") VALUES (?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
      stmt.setInt(1, entity.getUnitID());
      stmt.executeUpdate();

      try (ResultSet rs = stmt.getGeneratedKeys()) {
        if (rs.next()) {
          int generatedId = rs.getInt(1);
          return new Totem(generatedId, entity.getUnitID());
        } else { throw new SQLException("Falha ao obter ID gerado."); }
      }
    } catch (SQLException e) { throw new RuntimeException(e); }
  }

  @Override
  public void update(Totem entity) {
    throw new UnsupportedOperationException("Método ainda não implementado.");
  }

  @Override
  public void delete(Integer id) {
    final String sql = "DELETE FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, id);
      stmt.executeUpdate();
    } catch (SQLException e) { throw new RuntimeException(e); }
  }
}