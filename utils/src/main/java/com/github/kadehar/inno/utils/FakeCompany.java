package com.github.kadehar.inno.utils;

import com.github.javafaker.Company;
import com.github.javafaker.Faker;

public class FakeCompany {

    private static final Company COMPANY = new Faker().company();


    public static String name() {
        return COMPANY.name();
    }

    public static String description() {
        return COMPANY.catchPhrase();
    }
}
