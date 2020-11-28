package com.blank.ilia.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserAccess extends User {
    private String name;

    public UserAccess(String name, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
