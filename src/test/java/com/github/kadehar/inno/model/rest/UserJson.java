package com.github.kadehar.inno.model.rest;

import com.github.kadehar.inno.data.entity.AppUserEntity;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.Objects;

@Data
public class UserJson {
    @SerializedName("username")
    private final String username;
    @SerializedName("password")
    private final String password;

    public static UserJson fromEntity(AppUserEntity entity) {
        return new UserJson(
                entity.getLogin(),
                entity.getPassword()
        );
    }
}
