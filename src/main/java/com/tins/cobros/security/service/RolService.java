package com.tins.cobros.security.service;

import com.tins.cobros.security.model.Rol;
import com.tins.cobros.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getRolByName(String rol){
        return rolRepository.findByRolNombre(rol);
    }
}
