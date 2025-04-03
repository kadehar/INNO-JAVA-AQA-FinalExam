package com.github.kadehar.inno.db.dao.impl;

import com.github.kadehar.inno.db.dao.EmployeeDao;
import lombok.RequiredArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@RequiredArgsConstructor
public class EmployeeDaoJdbc implements EmployeeDao {

    private final Connection connection;

    @Override
    public void deleteEmployeeById(Long id) {
        try (PreparedStatement ps = connection.prepareStatement("delete from employee where id = ?;")) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
