package com.github.kadehar.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.kadehar.inno.page.component.Title;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutFormPage {
    private final SelenideElement firstName = $("#first-name");
    private final SelenideElement lastName = $("#last-name");
    private final SelenideElement zipCode = $("#postal-code");
    private final SelenideElement continueButton = $("#continue");


    public CheckoutFormPage() {
        new Title().assertIsVisible();
    }

    public CheckoutFormPage fillForm(String firstName, String lastName, String zipCode) {
        typeFirstName(firstName);
        typeLastName(lastName);
        typeZipCode(zipCode);
        return this;
    }

    @Step("Type {firstName} in \"First Name\" field")
    public CheckoutFormPage typeFirstName(String firstName) {
        this.firstName.shouldBe(Condition.visible).setValue(firstName);
        return this;
    }

    @Step("Type {lastName} in \"Last Name\" field")
    public CheckoutFormPage typeLastName(String lastName) {
        this.lastName.shouldBe(Condition.visible).setValue(lastName);
        return this;
    }

    @Step("Type {zipCode} in \"Zip/Postal Code\" field")
    public CheckoutFormPage typeZipCode(String zipCode) {
        this.zipCode.shouldBe(Condition.visible).setValue(zipCode);
        return this;
    }

    @Step("Click \"Continue\" button")
    public CheckoutOverviewPage continueCheckout() {
        continueButton.shouldBe(Condition.visible).click();
        return new CheckoutOverviewPage();
    }
}
