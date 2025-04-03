package com.github.kadehar.inno.jupiter.extension;

import com.github.kadehar.inno.model.rest.UserJson;
import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.data.entity.AppUserEntity;
import com.github.kadehar.inno.data.entity.CompanyEntity;
import com.github.kadehar.inno.data.service.AppUsersDbClient;
import com.github.kadehar.inno.data.service.CompanyDbClient;
import com.github.kadehar.inno.jupiter.annotation.WithPreconditions;
import com.github.kadehar.inno.utils.TestStorageKey;
import com.github.kadehar.inno.utils.RandomDataUtils;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;

public class PreconditionsExtension implements BeforeEachCallback, AfterEachCallback {

    private static final Config CFG = Config.getInstance();
    public static final ExtensionContext.Namespace NAMESPACE =
            ExtensionContext.Namespace.create(PreconditionsExtension.class);

    private final AppUsersDbClient usersDbClient = new AppUsersDbClient();
    private final CompanyDbClient companyDbClient = new CompanyDbClient();

    @Override
    public void beforeEach(ExtensionContext context) {
        AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), WithPreconditions.class).ifPresent(_ -> {
            initCompany();
            initClient();
        });
        AnnotationSupport.findAnnotation(context.getRequiredTestClass(), WithPreconditions.class).ifPresent(_ -> {
            initCompany();
            initClient();
        });
    }

    @Override
    public void afterEach(ExtensionContext context) {
        usersDbClient.deleteUserById(getUserId());
        companyDbClient.deleteCompanyById(getCompanyId());
    }

    public static void setUserId(Long userId) {
        TestMethodContextExtension.context().getStore(NAMESPACE).put(TestStorageKey.USER_ID, userId);
    }

    public static Long getUserId() {
        return TestMethodContextExtension.context().getStore(NAMESPACE).get(TestStorageKey.USER_ID, Long.class);
    }

    public static void setUser(UserJson user) {
        TestMethodContextExtension.context().getStore(NAMESPACE).put(TestStorageKey.USER, user);
    }

    public static UserJson getUser() {
        return TestMethodContextExtension.context().getStore(NAMESPACE).get(TestStorageKey.USER, UserJson.class);
    }

    public static void setCompanyId(Long companyId) {
        TestMethodContextExtension.context().getStore(NAMESPACE).put(TestStorageKey.COMPANY_ID, companyId);
    }

    public static Long getCompanyId() {
        return TestMethodContextExtension.context().getStore(NAMESPACE).get(TestStorageKey.COMPANY_ID, Long.class);
    }

    private void initClient() {
        AppUserEntity user = new AppUserEntity();
        user.setActive(true);
        user.setRole(CFG.dbUserRole());
        user.setLogin(CFG.dbUserName());
        user.setDisplayName(RandomDataUtils.randomNickname());
        user.setPassword(CFG.dbUserPassword());
        user = usersDbClient.createUser(user);
        setUserId(user.getId());
        setUser(UserJson.fromEntity(user));
    }

    private void initCompany() {
        CompanyEntity company = new CompanyEntity();
        company.setActive(true);
        company.setName(RandomDataUtils.randomCompanyName());
        company.setDescription(RandomDataUtils.randomCompanyDescription());
        company = companyDbClient.createCompany(company);
        setCompanyId(company.getId());
    }
}