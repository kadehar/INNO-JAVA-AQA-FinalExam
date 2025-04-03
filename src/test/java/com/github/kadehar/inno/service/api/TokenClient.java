package com.github.kadehar.inno.service.api;

import com.github.kadehar.inno.api.RestClient;
import com.github.kadehar.inno.api.TokenApi;
import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.model.rest.UserJson;
import io.qameta.allure.Param;
import io.qameta.allure.Step;
import io.qameta.allure.model.Parameter;
import lombok.SneakyThrows;

public class TokenClient extends RestClient {
    private static final Config CFG = Config.getInstance();

    private final TokenApi tokenApi;

    public TokenClient() {
        super(CFG.apiBaseUrl());
        this.tokenApi = create(TokenApi.class);
    }

    @SneakyThrows
    @Step("Get auth token for user {userJson.username}")
    public String fetchToken(@Param(mode = Parameter.Mode.HIDDEN) UserJson userJson) {
        return tokenApi.fetchToken(userJson).execute().body().getUserToken();
    }
}
