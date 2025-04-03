package com.github.kadehar.inno.utils;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumbers {
    public static int itemsCount() {
        return itemsCount(1, 6);
    }

    public static int itemsCount(int origin, int bound) {
        return ThreadLocalRandom.current().nextInt(origin, bound);
    }
}
