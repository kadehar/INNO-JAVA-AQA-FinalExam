package com.github.kadehar.inno.data.dao;

import com.github.kadehar.inno.data.entity.CompanyEntity;

public interface CompanyDao {
    CompanyEntity create(CompanyEntity company);
    void deleteCompanyById(Long id);
}
