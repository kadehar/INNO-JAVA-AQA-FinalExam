package com.github.kadehar.inno.api.core;

import com.github.kadehar.inno.jupiter.extension.ApiLoginExtension;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class AuthInterceptor implements Interceptor {
    private static final String X_CLIENT_TOKEN_HEADER = "x-client-token";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader(X_CLIENT_TOKEN_HEADER, ApiLoginExtension.getToken());

        return chain.proceed(builder.build());
    }
}
