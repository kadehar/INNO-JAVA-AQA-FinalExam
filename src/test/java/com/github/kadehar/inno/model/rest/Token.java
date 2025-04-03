package com.github.kadehar.inno.model.rest;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Token {
    @SerializedName("userToken")
    private final String userToken;
}