package com.github.kadehar.inno.data.service;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.data.dao.impl.AppUsersDaoJdbc;
import com.github.kadehar.inno.data.entity.AppUserEntity;
import com.github.kadehar.inno.db.DbData;

import static com.github.kadehar.inno.db.Databases.transaction;

public class AppUsersDbClient {

    private static final Config CFG = Config.getInstance();

    public AppUserEntity createUser(AppUserEntity entity) {
        return transaction(connection -> {
            return new AppUsersDaoJdbc(connection).create(entity);
        }, new DbData(CFG.dbUrl(), CFG.dbLogin(), CFG.dbPassword()));
    }

    public void deleteUserById(Long id) {
        transaction(connection -> {
            new AppUsersDaoJdbc(connection).deleteAppUserById(id);
        }, new DbData(CFG.dbUrl(), CFG.dbLogin(), CFG.dbPassword()));
    }
}
