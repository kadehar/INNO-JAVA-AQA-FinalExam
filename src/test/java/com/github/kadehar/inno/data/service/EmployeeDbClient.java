package com.github.kadehar.inno.data.service;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.data.dao.impl.EmployeeDaoJdbc;

import static com.github.kadehar.inno.data.Databases.transaction;

public class EmployeeDbClient {

    private static final Config CFG = Config.getInstance();

    public void deleteEmployeeById(Long id) {
        transaction(connection -> {
            new EmployeeDaoJdbc(connection).deleteEmployeeById(id);
        }, CFG.dbUrl());
    }
}
