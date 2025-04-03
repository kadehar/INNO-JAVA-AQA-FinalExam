package com.github.kadehar.inno.service.api;

import com.github.kadehar.inno.api.RestClient;
import com.github.kadehar.inno.api.EmployeeApi;
import com.github.kadehar.inno.api.core.AuthInterceptor;
import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.model.rest.EmployeeJson;
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
    public long createNewEmployee(EmployeeJson employeeJson) {
        return employeeApi.createNew(employeeJson).execute().body().getValue();
    }

    @SneakyThrows
    public List<EmployeeJson> findAll(long companyId) {
        return employeeApi.findAll(companyId).execute().body();
    }

    @SneakyThrows
    public EmployeeJson getById(long id) {
        return employeeApi.getEmployee(id).execute().body();
    }

    @SneakyThrows
    public EmployeeJson update(EmployeeJson employeeJson) {
        return employeeApi.update(employeeJson.getId(), employeeJson).execute().body();
    }
}
