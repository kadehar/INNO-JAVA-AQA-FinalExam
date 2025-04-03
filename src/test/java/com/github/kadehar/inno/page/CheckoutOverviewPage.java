package com.github.kadehar.inno.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.github.kadehar.inno.model.web.Currency;
import com.github.kadehar.inno.page.component.Title;
import com.github.kadehar.inno.utils.ElementUtil;
import com.github.kadehar.inno.utils.PriceUtil;
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
        BigDecimal tax = PriceUtil.getPrice(
                ElementUtil.getText(taxPrice),
                "Tax: ",
                currency
        );
        BigDecimal subtotal = PriceUtil.getPrice(
                ElementUtil.getText(subtotalPrice),
                "Item total: ",
                currency
        );
        String total = PriceUtil.getTotal(tax, subtotal);
        totalPrice.shouldBe(Condition.visible).shouldHave(Condition.text(total));
        return this;
    }
}
