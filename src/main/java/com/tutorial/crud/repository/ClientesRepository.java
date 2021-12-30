package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.entity.Rutas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ClientesRepository  extends CrudRepository<Clientes, Integer> {
    Optional<Clientes> findByCodigo(String codigo);
    Optional<List<Clientes>> findAllByRutas(Rutas rutas);
    Optional<List<Clientes>> findByCodigoContainsOrNombreContains(String codigo, String nombre);
    boolean existsByCodigo(String codigo);
}
