package com.github.kadehar.inno.utils;

import com.github.javafaker.Address;
import com.github.javafaker.Faker;

public class FakeAddress {
    private static final Address ADDRESS = new Faker().address();

    public static String zipCode() {
        return ADDRESS.zipCode();
    }
}
