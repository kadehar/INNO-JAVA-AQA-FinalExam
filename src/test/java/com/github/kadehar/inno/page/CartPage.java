package com.github.kadehar.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.kadehar.inno.page.component.Title;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CartPage {
    private final SelenideElement checkoutButton = $("#checkout");

    public CartPage() {
        new Title().assertIsVisible();
    }

    @Step("Go to checkout form page")
    public CheckoutFormPage goToCheckoutForm() {
        checkoutButton.shouldBe(Condition.visible).click();
        return new CheckoutFormPage();
    }
}
