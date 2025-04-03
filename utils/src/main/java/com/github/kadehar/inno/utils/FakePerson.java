package com.github.kadehar.inno.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class FakePerson {
    private static final Name NAME = new Faker().name();

    public static String nick() {
        return NAME.username();
    }

    public static String firstName() {
        return NAME.firstName();
    }

    public static String lastName() {
        return NAME.lastName();
    }

    public static String middleName() {
        return firstName();
    }
}
