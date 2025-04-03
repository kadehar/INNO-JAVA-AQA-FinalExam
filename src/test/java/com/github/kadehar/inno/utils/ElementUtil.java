package com.github.kadehar.inno.utils;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public class ElementUtil {
    public static void type(SelenideElement element, String value) {
        element.shouldBe(Condition.visible).setValue(value);
    }

    public static String getText(SelenideElement element) {
        return element.shouldBe(Condition.visible).text();
    }
}
