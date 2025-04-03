package com.github.kadehar.inno.tests;

import com.github.kadehar.inno.jupiter.annotation.ApiLogin;
import com.github.kadehar.inno.jupiter.annotation.WebTest;
import com.github.kadehar.inno.jupiter.annotation.WithEmployee;
import com.github.kadehar.inno.jupiter.annotation.WithPreconditions;
import com.github.kadehar.inno.jupiter.arguments.UsersArgumentsProvider;
import com.github.kadehar.inno.jupiter.extension.PreconditionsExtension;
import com.github.kadehar.inno.model.rest.EmployeeJson;
import com.github.kadehar.inno.model.web.User;
import com.github.kadehar.inno.page.LoginPage;
import com.github.kadehar.inno.service.api.EmployeeClient;
import com.github.kadehar.inno.utils.FakeAddress;
import com.github.kadehar.inno.utils.FakePerson;
import com.github.kadehar.inno.utils.RandomNumbers;
import io.qameta.allure.Param;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.List;

import static io.qameta.allure.model.Parameter.Mode.HIDDEN;
import static org.assertj.core.api.Assertions.assertThat;

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

    private final EmployeeClient employeeClient = new EmployeeClient();

    @Test
    @WithPreconditions
    @ApiLogin
    @WithEmployee
    @DisplayName("Can get employees by their company id")
    void canGetEmployeesByCompanyId(EmployeeJson employee) {
        List<EmployeeJson> employees = employeeClient.findAll(PreconditionsExtension.getCompanyId());
        assertThat(employees).contains(employee);
    }
}
