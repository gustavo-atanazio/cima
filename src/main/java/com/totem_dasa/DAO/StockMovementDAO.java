package com.totem_dasa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.totem_dasa.infra.ConnectionFactory;
import com.totem_dasa.models.StockMovement;

public class StockMovementDAO implements CRUD<StockMovement, Integer> {
  private final String TABLE_NAME = "alteracao";
  private final String ID_COLUMN = "id";
  private final String EMPLOYEE_ID_COLUMN = "id_funcionario";
  private final String TOTEM_ID_COLUMN = "totem_id";
  private final String DATE_COLUMN = "data";
  private final Connection connection = new ConnectionFactory().getConnection();

  @Override
  public List<StockMovement> findAll() {
    final String sql = "SELECT * FROM " + TABLE_NAME;
    List<StockMovement> movements = new ArrayList<>();
    
    try (
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery()
    ) {
      while (rs.next()) {
        movements.add(new StockMovement(
          rs.getInt(ID_COLUMN),
          rs.getInt(EMPLOYEE_ID_COLUMN),
          rs.getInt(TOTEM_ID_COLUMN),
          rs.getTimestamp(DATE_COLUMN).toLocalDateTime()
        ));
      }
    } catch (SQLException e) { throw new RuntimeException(e); }

    return movements;
  }

  @Override
  public Optional<StockMovement> findById(Integer id) {
    final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(new StockMovement(
          rs.getInt(ID_COLUMN),
          rs.getInt(EMPLOYEE_ID_COLUMN),
          rs.getInt(TOTEM_ID_COLUMN),
          rs.getTimestamp(DATE_COLUMN).toLocalDateTime()
        ));
      }
    } catch (SQLException e) { throw new RuntimeException(e); }

    return Optional.empty();
  }

  @Override
  public StockMovement create(StockMovement entity) {
    final String sql = "INSERT INTO " + TABLE_NAME + " (" 
      + EMPLOYEE_ID_COLUMN + ", " 
      + TOTEM_ID_COLUMN + ", " 
      + DATE_COLUMN + ") VALUES (?, ?, ?)"
    ;

    try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
      stmt.setInt(1, entity.getEmployeeID());
      stmt.setInt(2, entity.getTotemID());
      stmt.setTimestamp(3, java.sql.Timestamp.valueOf(entity.getDate()));

      stmt.executeUpdate();

      try (ResultSet rs = stmt.getGeneratedKeys()) {
        if (rs.next()) {
          int generatedId = rs.getInt(1);

          return new StockMovement(
            generatedId,
            entity.getEmployeeID(),
            entity.getTotemID(),
            entity.getDate()
          );
        } else { throw new SQLException("Falha ao obter ID gerado."); }
      }
    } catch (SQLException e) { throw new RuntimeException(e); }
  }

  @Override
  public void update(StockMovement entity) {
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