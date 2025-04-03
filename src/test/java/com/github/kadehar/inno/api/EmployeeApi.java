package com.github.kadehar.inno.api;

import com.github.kadehar.inno.model.rest.EmployeeJson;
import com.github.kadehar.inno.model.rest.Id;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface EmployeeApi {
    @POST("employee")
    Call<Id> createNew(@Body EmployeeJson employeeJson);
    @GET("employee")
    Call<List<EmployeeJson>> findAll(@Query("company") long companyId);
    @GET("employee/{id}")
    Call<EmployeeJson> getEmployee(@Path("id") long id);
    @PATCH("employee/{id}")
    Call<EmployeeJson> update(@Path("id") long id, @Body EmployeeJson employeeJson);
}
