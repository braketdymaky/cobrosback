package com.tins.cobros.security.model;

import com.sun.istack.NotNull;
import com.tins.cobros.security.enums.RolNombre;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tins_roles_usuario")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CDROL")
    private int CDROL;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "DSNAME")
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public int getCDROL() {
        return CDROL;
    }

    public void setCDROL(int CDROL) {
        this.CDROL = CDROL;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}
