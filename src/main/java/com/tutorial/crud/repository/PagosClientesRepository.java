package com.tutorial.crud.repository;

import com.tutorial.crud.entity.PagosClientes;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PagosClientesRepository extends CrudRepository<PagosClientes, Integer> {
    Optional<List<PagosClientes>> findByCliente(String codigo);
    Optional<List<PagosClientes>> findByCobrador(String codigo);
    Optional<List<PagosClientes>> findAllByFchPagoBetween(Date desde, Date hasta);
}
