package com.github.kadehar.inno.db.entity;

import lombok.Data;

@Data
public class CompanyEntity {
    private Long id;
    private boolean active;
    private String name;
    private String description;
}
