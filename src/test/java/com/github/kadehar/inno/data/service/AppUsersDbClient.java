package com.github.kadehar.inno.data.service;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.data.dao.impl.AppUsersDaoJdbc;
import com.github.kadehar.inno.data.entity.AppUserEntity;

import static com.github.kadehar.inno.data.Databases.transaction;

public class AppUsersDbClient {

    private static final Config CFG = Config.getInstance();

    public AppUserEntity createUser(AppUserEntity entity) {
        return transaction(connection -> {
            return new AppUsersDaoJdbc(connection).create(entity);
        }, CFG.dbUrl());
    }

    public void deleteUserById(Long id) {
        transaction(connection -> {
            new AppUsersDaoJdbc(connection).deleteAppUserById(id);
        }, CFG.dbUrl());
    }
}
