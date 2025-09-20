package com.totem_dasa.DAO;

import java.util.List;
import java.util.Optional;

public interface CRUD<E, I> {
  // E - Entity, I - ID type

  List<E> findAll(); // GET
  Optional<E> findById(I id); // GET by ID
  E create(E entity); // POST
  void update(E entity); // PUT
  void delete(I id); // DELETE
}