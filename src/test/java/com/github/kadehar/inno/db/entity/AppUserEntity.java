package com.github.kadehar.inno.db.entity;

import lombok.Data;

@Data
public class AppUserEntity {
    private Long id;
    private boolean active;
    private String login;
    private String password;
    private String displayName;
    private String role;
}
