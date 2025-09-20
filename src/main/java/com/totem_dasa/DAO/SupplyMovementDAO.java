package com.totem_dasa.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.totem_dasa.infra.ConnectionFactory;
import com.totem_dasa.models.SupplyMovement;

public class SupplyMovementDAO implements CRUD<SupplyMovement, Integer> {
  private final String TABLE_NAME = "movimentacao_insumo";
  private final String ID_COLUMN = "id";
  private final String SUPPLY_ID_COLUMN = "id_insumo";
  private final String QUANTITY_COLUMN = "quantidade";
  private final String TYPE_COLUMN = "tipo";
  private final String STOCK_MOVEMENT_ID_COLUMN = "alteracao_id";
  private final Connection connection = new ConnectionFactory().getConnection();

  @Override
  public List<SupplyMovement> findAll() {
    final String sql = "SELECT * FROM " + TABLE_NAME;
    List<SupplyMovement> movements = new ArrayList<>();
    
    try (
      PreparedStatement stmt = connection.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery()
    ) {
      while (rs.next()) {
        movements.add(new SupplyMovement(
          rs.getInt(ID_COLUMN),
          rs.getInt(SUPPLY_ID_COLUMN),
          rs.getInt(QUANTITY_COLUMN),
          rs.getString(TYPE_COLUMN).charAt(0),
          rs.getInt(STOCK_MOVEMENT_ID_COLUMN)
        ));
      }
    } catch (SQLException e) { throw new RuntimeException(e); }

    return movements;
  }

  @Override
  public Optional<SupplyMovement> findById(Integer id) {
    final String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + ID_COLUMN + " = ?";

    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
      stmt.setInt(1, id);
      ResultSet rs = stmt.executeQuery();

      if (rs.next()) {
        return Optional.of(new SupplyMovement(
          rs.getInt(ID_COLUMN),
          rs.getInt(SUPPLY_ID_COLUMN),
          rs.getInt(QUANTITY_COLUMN),
          rs.getString(TYPE_COLUMN).charAt(0),
          rs.getInt(STOCK_MOVEMENT_ID_COLUMN)
        ));
      }
    } catch (SQLException e) { throw new RuntimeException(e); }

    return Optional.empty();
  }

  @Override
  public SupplyMovement create(SupplyMovement entity) {
    final String sql = "INSERT INTO " + TABLE_NAME + " ("
      + SUPPLY_ID_COLUMN + ", "
      + QUANTITY_COLUMN + ", "
      + TYPE_COLUMN + ", "
      + STOCK_MOVEMENT_ID_COLUMN
    + ") VALUES (?, ?, ?, ?)";

    try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
      stmt.setInt(1, entity.getSupplyID());
      stmt.setInt(2, entity.getQuantity());
      stmt.setString(3, String.valueOf(entity.getType()));
      stmt.setInt(4, entity.getStockMovementID());

      stmt.executeUpdate();

      try (ResultSet rs = stmt.getGeneratedKeys()) {
        if (rs.next()) {
          int generatedId = rs.getInt(1);
          
          return new SupplyMovement(
            generatedId,
            entity.getSupplyID(),
            entity.getQuantity(),
            entity.getType(),
            entity.getStockMovementID()
          );
        } else { throw new SQLException("Falha ao obter ID gerado."); }
      }
    } catch (SQLException e) { throw new RuntimeException(e); }
  }

  @Override
  public void update(SupplyMovement entity) {
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