package com.github.kadehar.inno.jupiter.extension;

import com.github.kadehar.inno.api.service.TokenClient;
import com.github.kadehar.inno.model.rest.UserJson;
import com.github.kadehar.inno.jupiter.annotation.ApiLogin;
import com.github.kadehar.inno.utils.TestStorageKey;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

public class ApiLoginExtension implements BeforeEachCallback {

    public static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(ApiLoginExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) {
        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), ApiLogin.class).ifPresent(_ -> {
            UserJson userJson = PreconditionsExtension.getUser();
            TokenClient tokenClient = new TokenClient();
            String token = tokenClient.fetchToken(userJson);
            setToken(token);
        });
    }

    public static void setToken(String token) {
        TestMethodContextExtension.context().getStore(NAMESPACE).put(TestStorageKey.TOKEN, token);
    }

    public static String getToken() {
        return TestMethodContextExtension.context().getStore(NAMESPACE).get(TestStorageKey.TOKEN, String.class);
    }
}
