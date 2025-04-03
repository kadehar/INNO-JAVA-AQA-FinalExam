package com.github.kadehar.inno.service.db;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.db.dao.impl.AppUsersDaoJdbc;
import com.github.kadehar.inno.db.entity.AppUserEntity;
import com.github.kadehar.inno.db.DbData;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

import static com.github.kadehar.inno.db.Databases.transaction;

public class AppUsersDbClient {

    private static final Config CFG = Config.getInstance();

    @Step("Insert new user {entity.login} data in DB")
    public AppUserEntity createUser(@Param(mode = Parameter.Mode.HIDDEN) AppUserEntity entity) {
        return transaction(connection -> {
            return new AppUsersDaoJdbc(connection).create(entity);
        }, new DbData(CFG.dbUrl(), CFG.dbLogin(), CFG.dbPassword()));
    }

    @Step("Delete user by its ID")
    public void deleteUserById(Long id) {
        transaction(connection -> {
            new AppUsersDaoJdbc(connection).deleteAppUserById(id);
        }, new DbData(CFG.dbUrl(), CFG.dbLogin(), CFG.dbPassword()));
    }
}
