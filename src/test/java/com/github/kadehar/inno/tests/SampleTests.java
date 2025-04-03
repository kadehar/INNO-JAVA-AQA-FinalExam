package com.github.kadehar.inno.tests;

import com.github.kadehar.inno.jupiter.annotation.WebTest;
import com.github.kadehar.inno.jupiter.arguments.UsersArgumentsProvider;
import com.github.kadehar.inno.model.web.User;
import com.github.kadehar.inno.page.LoginPage;
import com.github.kadehar.inno.utils.FakeAddress;
import com.github.kadehar.inno.utils.FakePerson;
import com.github.kadehar.inno.utils.RandomNumbers;
import io.qameta.allure.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;

public class SampleTests {

    @ParameterizedTest
    @ArgumentsSource(UsersArgumentsProvider.class)
    @DisplayName("Verify that checkout sum is valid")
    @WebTest
    void verifyCheckoutSumIsValid(@Param(value = "user", mode = HIDDEN) User user) {
        LoginPage.goTo()
                .login(user)
                .addProductsToCart(RandomNumbers.itemsCount())
                .goToCart()
                .goToCheckoutForm()
                .fillForm(
                        FakePerson.firstName(),
                        FakePerson.lastName(),
                        FakeAddress.zipCode()
                )
                .continueCheckout()
                .verifyTotalPrice();
    }
}
