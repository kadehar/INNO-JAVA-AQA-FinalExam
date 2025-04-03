package com.github.kadehar.inno.model.rest;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Id {
    @SerializedName("id")
    private final Long value;
}
