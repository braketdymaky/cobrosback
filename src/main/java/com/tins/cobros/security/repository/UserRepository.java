package com.tins.cobros.security.repository;

import com.tins.cobros.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByName(String name);
    boolean existsByName(String name);


}
