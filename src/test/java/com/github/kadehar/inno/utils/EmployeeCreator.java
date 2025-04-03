package com.github.kadehar.inno.utils;

import com.github.kadehar.inno.model.rest.EmployeeJson;
import com.github.kadehar.inno.jupiter.extension.PreconditionsExtension;

import java.time.LocalDate;

public class EmployeeCreator {
    public static EmployeeJson newEmployee() {
        return new EmployeeJson(
                null,
                FakePerson.firstName(),
                FakePerson.lastName(),
                FakePerson.middleName(),
                PreconditionsExtension.getCompanyId(),
                FakeData.email(),
                FakeData.url(),
                FakeData.phoneNumber(),
                LocalDate.now().minusYears(30).toString(),
                true
        );
    }
}
