package com.github.kadehar.inno.api.core;

import com.github.kadehar.inno.config.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.HEADERS;

public abstract class RestClient {
    protected static final Config CFG = Config.getInstance();

    private static final Gson GSON = new GsonBuilder().setLenient().setPrettyPrinting().create();

    private final OkHttpClient okHttpClient;
    private final Retrofit retrofit;

    public RestClient(String baseUrl) {
        this(baseUrl, false, GsonConverterFactory.create(GSON), HEADERS);
    }

    public RestClient(String baseUrl, boolean followRedirect) {
        this(baseUrl, followRedirect, GsonConverterFactory.create(GSON), HEADERS);
    }

    public RestClient(String baseUrl, Interceptor... interceptors) {
        this(baseUrl, false, GsonConverterFactory.create(GSON), HEADERS, interceptors);
    }

    public RestClient(String baseUrl, boolean followRedirect, Interceptor... interceptors) {
        this(baseUrl, followRedirect, GsonConverterFactory.create(GSON), HEADERS, interceptors);
    }

    public RestClient(String baseUrl, HttpLoggingInterceptor.Level loggingLevel) {
        this(baseUrl, false, GsonConverterFactory.create(GSON), loggingLevel);
    }

    public RestClient(String baseUrl, HttpLoggingInterceptor.Level loggingLevel, Interceptor... interceptors) {
        this(baseUrl, false, GsonConverterFactory.create(GSON), loggingLevel, interceptors);
    }

    public RestClient(String baseUrl, Converter.Factory converterFactory, HttpLoggingInterceptor.Level loggingLevel) {
        this(baseUrl, false, converterFactory, loggingLevel);
    }

    public RestClient(String baseUrl, boolean followRedirect, HttpLoggingInterceptor.Level loggingLevel) {
        this(baseUrl, followRedirect, GsonConverterFactory.create(), loggingLevel);
    }

    public RestClient(String baseUrl, boolean followRedirect, Converter.Factory converterFactory, HttpLoggingInterceptor.Level loggingLevel) {
        this(baseUrl, followRedirect, converterFactory, loggingLevel, new Interceptor[0]);
    }

    public RestClient(String baseUrl, boolean followRedirect,
                      Converter.Factory converterFactory, HttpLoggingInterceptor.Level loggingLevel,
                      Interceptor... interceptors) {
        OkHttpClient.Builder okHttpBuilder = new OkHttpClient.Builder()
                .followRedirects(followRedirect);

        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                okHttpBuilder.addNetworkInterceptor(interceptor);
            }
        }
        okHttpBuilder.addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(loggingLevel));
        okHttpBuilder.addInterceptor(getAllureInterceptor());

        this.okHttpClient = okHttpBuilder.build();
        this.retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(converterFactory)
                .build();
    }

    public <T> T create(final Class<T> service) {
        return this.retrofit.create(service);
    }

    private AllureOkHttp3 getAllureInterceptor() {
        return new AllureOkHttp3()
                .setRequestTemplate("api-request.ftl")
                .setResponseTemplate("api-response.ftl");
    }
}
