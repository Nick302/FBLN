package com.example.freelancefbln.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    FBpars,
    FBsend,
    FBall,
    LNpars,
    LNsend,
    LNall,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
