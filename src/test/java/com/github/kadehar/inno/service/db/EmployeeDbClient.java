package com.github.kadehar.inno.service.db;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.db.dao.impl.EmployeeDaoJdbc;
import com.github.kadehar.inno.db.DbData;

import static com.github.kadehar.inno.db.Databases.transaction;

public class EmployeeDbClient {

    private static final Config CFG = Config.getInstance();

    public void deleteEmployeeById(Long id) {
        transaction(connection -> {
            new EmployeeDaoJdbc(connection).deleteEmployeeById(id);
        }, new DbData(CFG.dbUrl(), CFG.dbLogin(), CFG.dbPassword()));
    }
}
