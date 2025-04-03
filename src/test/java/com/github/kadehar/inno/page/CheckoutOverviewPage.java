package com.github.kadehar.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.kadehar.inno.model.web.Currency;
import com.github.kadehar.inno.page.component.Title;
import com.github.kadehar.inno.utils.DecimalPrice;
import io.qameta.allure.Step;

import java.math.BigDecimal;

import static com.codeborne.selenide.Selenide.$;

public class CheckoutOverviewPage {
    private final SelenideElement totalPrice = $("[data-test=total-label]");
    private final SelenideElement taxPrice = $("[data-test=tax-label]");
    private final SelenideElement subtotalPrice = $("[data-test=subtotal-label]");


    public CheckoutOverviewPage() {
        new Title().assertIsVisible();
    }

    public CheckoutOverviewPage verifyTotalPrice() {
        verifyTotalPrice(Currency.USD);
        return this;
    }

    @Step("Verify total price in {currency}")
    public CheckoutOverviewPage verifyTotalPrice(Currency currency) {
        String taxPriceText = taxPrice.shouldBe(Condition.visible).text()
                .replace("Tax: " + currency.getSign(), "");
        String subtotalPriceText = subtotalPrice.shouldBe(Condition.visible).text()
                .replace("Item total: " + currency.getSign(), "");
        BigDecimal tax = DecimalPrice.asDecimalPrice(taxPriceText);
        BigDecimal subtotal = DecimalPrice.asDecimalPrice(subtotalPriceText);
        String total = DecimalPrice.asText(tax, subtotal);
        totalPrice.shouldBe(Condition.visible).shouldHave(Condition.text(total));
        return this;
    }
}
