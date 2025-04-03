package com.github.kadehar.inno.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.github.javafaker.PhoneNumber;

public class FakeData {
    private static final Internet INTERNET = new Faker().internet();
    private static final PhoneNumber PHONE_NUMBER = new Faker().phoneNumber();

    public static String email() {
        return INTERNET.emailAddress();
    }

    public static String url() {
        return INTERNET.url();
    }

    public static String phoneNumber(String start) {
        return start + PHONE_NUMBER.subscriberNumber(7);
    }

    public static String phoneNumber() {
        return phoneNumber("7999");
    }
}
