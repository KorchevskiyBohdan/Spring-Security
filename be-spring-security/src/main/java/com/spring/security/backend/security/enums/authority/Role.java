package com.spring.security.backend.security.enums.authority;

import org.springframework.security.core.GrantedAuthority;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role implements GrantedAuthority {

    USER(1, "USER"),
    ADMIN(2, "ADMIN"),
    OPERATOR(3, "OPERATOR"),
    MANAGER(4, "MANAGER"),
    SUPER_ADMIN(5, "SUPER_ADMIN");

    String role;
    Integer id;

    Role(Integer id, String role) {
        this.id = id;
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public static Role toRole(Integer statusId){
        return switch (statusId) {
            case 1 -> USER;
            case 2 -> ADMIN;
            case 3 -> OPERATOR;
            case 4 -> MANAGER;
            case 5 -> SUPER_ADMIN;
            default -> null;
        };
    }
}
