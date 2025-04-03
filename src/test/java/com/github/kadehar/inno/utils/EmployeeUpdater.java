package com.github.kadehar.inno.utils;

import com.github.kadehar.inno.model.rest.EmployeeJson;

public class EmployeeUpdater {
    public static EmployeeJson update(EmployeeJson employeeJson, Field field) {
        switch (field) {
            case URL -> employeeJson.setUrl(RandomDataUtils.randomUrl());
            case EMAIL -> employeeJson.setEmail(RandomDataUtils.randomEmail());
            case PHONE -> employeeJson.setPhone(RandomDataUtils.randomPhone());
        }
        return employeeJson;
    }

    public enum Field {
        EMAIL, PHONE, URL
    }
}
