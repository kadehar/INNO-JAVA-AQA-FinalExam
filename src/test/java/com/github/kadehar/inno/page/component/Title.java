package com.github.kadehar.inno.page.component;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class Title {
    private final SelenideElement title = $(".title");

    public void assertIsVisible() {
        title.shouldBe(Condition.visible, Duration.ofSeconds(20));
    }
}
