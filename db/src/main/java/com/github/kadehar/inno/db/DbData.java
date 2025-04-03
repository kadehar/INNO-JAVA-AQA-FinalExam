package com.github.kadehar.inno.db;

import lombok.Data;

@Data
public class DbData {
    private final String jdbcUrl;
    private final String login;
    private final String password;
}
