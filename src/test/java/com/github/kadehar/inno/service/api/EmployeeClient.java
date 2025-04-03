package com.github.kadehar.inno.service.api;

import com.github.kadehar.inno.api.RestClient;
import com.github.kadehar.inno.api.EmployeeApi;
import com.github.kadehar.inno.api.core.AuthInterceptor;
import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.model.rest.EmployeeJson;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import lombok.SneakyThrows;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.List;

public class EmployeeClient extends RestClient {

    private static final Config CFG = Config.getInstance();

    private final EmployeeApi employeeApi;

    public EmployeeClient() {
        super(CFG.apiBaseUrl(), HttpLoggingInterceptor.Level.BODY, new AuthInterceptor());
        this.employeeApi = create(EmployeeApi.class);
    }

    @SneakyThrows
    @Step("Create new employee with name {employeeJson.firstName}")
    public long createNewEmployee(@Param(mode = Parameter.Mode.HIDDEN) EmployeeJson employeeJson) {
        return employeeApi.createNew(employeeJson).execute().body().getValue();
    }

    @SneakyThrows
    @Step("Find all employees by company ID")
    public List<EmployeeJson> findAll(long companyId) {
        return employeeApi.findAll(companyId).execute().body();
    }

    @SneakyThrows
    @Step("Find employee by its ID")
    public EmployeeJson getById(long id) {
        return employeeApi.getEmployee(id).execute().body();
    }

    @SneakyThrows
    @Step("Update employee with name {employeeJson.firstName}")
    public EmployeeJson update(@Param(mode = Parameter.Mode.HIDDEN) EmployeeJson employeeJson) {
        return employeeApi.update(employeeJson.getId(), employeeJson).execute().body();
    }
}
