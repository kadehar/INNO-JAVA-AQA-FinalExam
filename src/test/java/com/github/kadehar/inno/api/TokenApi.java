package com.github.kadehar.inno.api;

import com.github.kadehar.inno.model.rest.Token;
import com.github.kadehar.inno.model.rest.UserJson;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TokenApi {
    @POST("auth/login")
    Call<Token> fetchToken(@Body UserJson userJson);
}
