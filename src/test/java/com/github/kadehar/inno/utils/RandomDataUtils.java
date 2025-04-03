package com.github.kadehar.inno.utils;

import com.github.javafaker.Faker;

import java.util.concurrent.ThreadLocalRandom;

public class RandomDataUtils {

    private static final Faker FAKER = new Faker();

    public static String randomCompanyName() {
        return FAKER.company().name();
    }

    public static String randomCompanyDescription() {
        return FAKER.company().catchPhrase();
    }

    public static String randomNickname() {
        return FAKER.name().username();
    }

    public static String randomFirstName() {
        return FAKER.name().firstName();
    }

    public static String randomLastName() {
        return FAKER.name().lastName();
    }

    public static String randomMiddleName() {
        return randomFirstName();
    }

    public static String randomEmail() {
        return FAKER.internet().emailAddress();
    }

    public static String randomUrl() {
        return FAKER.internet().url();
    }

    public static String randomPhone() {
        return "7999" + FAKER.phoneNumber().subscriberNumber(7);
    }

    public static String zipCode() {
        return FAKER.address().zipCode();
    }

    public static int itemsCount() {
        return ThreadLocalRandom.current().nextInt(1, 6);
    }
}