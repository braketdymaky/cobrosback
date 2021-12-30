package com.tins.cobros.security.dto;

import com.sun.istack.NotNull;

import java.util.HashSet;
import java.util.Set;

public class NewUser {
    @NotNull
    private String name;
    @NotNull
    private String password;
    private Set<String> roles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
