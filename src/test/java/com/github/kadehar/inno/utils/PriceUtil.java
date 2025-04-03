package com.github.kadehar.inno.utils;

import com.github.kadehar.inno.model.web.Currency;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class PriceUtil {
    public static BigDecimal getPrice(String original, String textToReplace, Currency currency) {
        return new BigDecimal(original.replace(textToReplace + currency.sign(), ""));
    }

    public static String getTotal(BigDecimal tax, BigDecimal subtotal) {
        DecimalFormat decimalFormat = new DecimalFormat("##0.00");
        return decimalFormat.format(tax.add(subtotal)).replace(",", ".");
    }
}
