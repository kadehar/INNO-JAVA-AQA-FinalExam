package com.github.kadehar.inno.utils;

import com.github.kadehar.inno.model.rest.EmployeeJson;

public class EmployeeUpdater {
    public static EmployeeJson update(EmployeeJson employeeJson, Field field) {
        switch (field) {
            case URL -> employeeJson.setUrl(FakeData.url());
            case EMAIL -> employeeJson.setEmail(FakeData.email());
            case PHONE -> employeeJson.setPhone(FakeData.phoneNumber());
        }
        return employeeJson;
    }

    public enum Field {
        EMAIL, PHONE, URL
    }
}
