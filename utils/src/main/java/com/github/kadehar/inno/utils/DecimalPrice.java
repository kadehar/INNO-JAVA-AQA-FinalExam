package com.github.kadehar.inno.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DecimalPrice {
    public static BigDecimal asDecimalPrice(String price) {
        return new BigDecimal(price);
    }

    public static String asText(BigDecimal... decimals) {
        BigDecimal first = decimals[0];
        BigDecimal result = BigDecimal.ZERO;
        for (int i = 1; i < decimals.length; i++) {
            result = first.add(decimals[i]);
        }
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        return decimalFormat.format(result).replace(",", ".");
    }
}
