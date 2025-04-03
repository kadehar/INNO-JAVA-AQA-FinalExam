package com.github.kadehar.inno.tests;

import com.github.kadehar.inno.api.service.EmployeeClient;
import com.github.kadehar.inno.jupiter.annotation.ApiLogin;
import com.github.kadehar.inno.jupiter.annotation.WithEmployee;
import com.github.kadehar.inno.jupiter.annotation.WithPreconditions;
import com.github.kadehar.inno.jupiter.extension.PreconditionsExtension;
import com.github.kadehar.inno.model.rest.EmployeeJson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@WithPreconditions
public class AttachTests {

    private final EmployeeClient employeeClient = new EmployeeClient();

    @Test
    @ApiLogin
    @WithEmployee
    @DisplayName("Can get employees by their company id")
    void canGetEmployeesByCompanyId(EmployeeJson employee) {
        List<EmployeeJson> employees = employeeClient.findAll(PreconditionsExtension.getCompanyId());
        System.out.println(employees);
    }
}
