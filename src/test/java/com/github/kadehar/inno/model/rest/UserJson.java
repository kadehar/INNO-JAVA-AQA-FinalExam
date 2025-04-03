package com.github.kadehar.inno.model.rest;

import com.github.kadehar.inno.db.entity.AppUserEntity;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

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
