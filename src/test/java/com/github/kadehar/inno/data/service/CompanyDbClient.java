package com.github.kadehar.inno.data.service;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.data.dao.impl.CompanyDaoJdbc;
import com.github.kadehar.inno.data.entity.CompanyEntity;

import static com.github.kadehar.inno.data.Databases.transaction;

public class CompanyDbClient {

    private static final Config CFG = Config.getInstance();

    public CompanyEntity createCompany(CompanyEntity company) {
        return transaction(connection -> {
            return new CompanyDaoJdbc(connection).create(company);
        }, CFG.dbUrl());
    }

    public void deleteCompanyById(Long id) {
        transaction(connection -> {
            new CompanyDaoJdbc(connection).deleteCompanyById(id);
        }, CFG.dbUrl());
    }
}
