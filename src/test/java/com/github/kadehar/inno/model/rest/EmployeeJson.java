package com.github.kadehar.inno.model.rest;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeJson {
    @SerializedName("id")
    private Long id;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("middleName")
    private String middleName;
    @SerializedName("companyId")
    private Long companyId;
    @SerializedName("email")
    private String email;
    @SerializedName("url")
    private String url;
    @SerializedName("phone")
    private String phone;
    @SerializedName("birthdate")
    private String birthDate;
    @SerializedName("isActive")
    private Boolean active;
}
