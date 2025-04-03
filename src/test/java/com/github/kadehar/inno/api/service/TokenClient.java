package com.github.kadehar.inno.api.service;

import com.github.kadehar.inno.api.core.RestClient;
import com.github.kadehar.inno.api.core.TokenApi;
import com.github.kadehar.inno.model.rest.UserJson;

import java.io.IOException;

public class TokenClient extends RestClient {

    private final TokenApi tokenApi;

    public TokenClient() {
        super(CFG.apiBaseUrl());
        this.tokenApi = create(TokenApi.class);
    }

    public String fetchToken(UserJson userJson) {
        try {
            return tokenApi.fetchToken(userJson).execute().body().getUserToken();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
