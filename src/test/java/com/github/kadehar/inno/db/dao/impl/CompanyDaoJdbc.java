package com.github.kadehar.inno.db.dao.impl;

import com.github.kadehar.inno.db.dao.CompanyDao;
import com.github.kadehar.inno.db.entity.CompanyEntity;
import lombok.RequiredArgsConstructor;

import java.sql.*;

@RequiredArgsConstructor
public class CompanyDaoJdbc implements CompanyDao {

    private final Connection connection;

    @Override
    public CompanyEntity create(CompanyEntity company) {
        try (PreparedStatement ps = connection.prepareStatement(
                "insert into company(is_active, \"name\", description)" +
                        "values (?, ?, ?);",
                Statement.RETURN_GENERATED_KEYS
        )) {
            ps.setBoolean(1, company.isActive());
            ps.setString(2, company.getName());
            ps.setString(3, company.getDescription());

            ps.executeUpdate();
            long id;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    id = rs.getLong("id");
                } else {
                    throw new SQLException("Can't find id in ResultSet");
                }
            }
            company.setId(id);
            return company;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteCompanyById(Long id) {
        try (PreparedStatement ps = connection.prepareStatement("delete from company where id = ?;")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
