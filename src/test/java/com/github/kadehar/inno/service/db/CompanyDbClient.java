package com.github.kadehar.inno.service.db;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.db.dao.impl.CompanyDaoJdbc;
import com.github.kadehar.inno.db.entity.CompanyEntity;
import com.github.kadehar.inno.db.DbData;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;

import static com.github.kadehar.inno.db.Databases.transaction;

public class CompanyDbClient {

    private static final Config CFG = Config.getInstance();

    @Step("Insert new company {company.name} data in DB")
    public CompanyEntity createCompany(@Param(mode = Parameter.Mode.HIDDEN) CompanyEntity company) {
        return transaction(connection -> {
            return new CompanyDaoJdbc(connection).create(company);
        }, new DbData(CFG.dbUrl(), CFG.dbLogin(), CFG.dbPassword()));
    }

    @Step("Delete company by its ID")
    public void deleteCompanyById(Long id) {
        transaction(connection -> {
            new CompanyDaoJdbc(connection).deleteCompanyById(id);
        }, new DbData(CFG.dbUrl(), CFG.dbLogin(), CFG.dbPassword()));
    }
}
