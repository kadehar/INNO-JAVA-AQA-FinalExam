package com.github.kadehar.inno.config;

import com.github.kadehar.inno.config.api.ApiConfig;
import com.github.kadehar.inno.config.db.DbConfig;
import com.github.kadehar.inno.config.users.UsersConfig;
import com.github.kadehar.inno.config.web.WebConfig;
import org.aeonbits.owner.ConfigFactory;

public enum LocalConfig implements Config {
    INSTANCE;

    private static final WebConfig WEB_CONFIG = ConfigFactory.create(
            WebConfig.class,
            System.getProperties()
    );
    private static final UsersConfig USERS_CONFIG = ConfigFactory.create(
            UsersConfig.class,
            System.getProperties()
    );
    private static final ApiConfig API_CONFIG = ConfigFactory.create(
            ApiConfig.class,
            System.getProperties()
    );
    private static final DbConfig DB_CONFIG = ConfigFactory.create(
            DbConfig.class,
            System.getProperties()
    );

    @Override
    public String dbUrl() {
        return DB_CONFIG.url();
    }

    @Override
    public String dbLogin() {
        return DB_CONFIG.login();
    }

    @Override
    public String dbPassword() {
        return DB_CONFIG.password();
    }

    @Override
    public String dbUserRole() {
        return DB_CONFIG.role();
    }

    @Override
    public String dbUserName() {
        return DB_CONFIG.userLogin();
    }

    @Override
    public String dbUserPassword() {
        return DB_CONFIG.userPassword();
    }

    @Override
    public String apiBaseUrl() {
        return API_CONFIG.url();
    }

    @Override
    public String webBaseUrl() {
        return WEB_CONFIG.baseUrl();
    }

    @Override
    public String browser() {
        return WEB_CONFIG.browser();
    }

    @Override
    public String pageLoadStrategy() {
        return WEB_CONFIG.pageLoadStrategy();
    }

    @Override
    public Boolean headless() {
        return WEB_CONFIG.headless();
    }

    @Override
    public Long timeout() {
        return WEB_CONFIG.timeout();
    }

    @Override
    public Boolean enableSteps() {
        return WEB_CONFIG.enableSteps();
    }

    @Override
    public String standardWebUser() {
        return USERS_CONFIG.standardUser();
    }

    @Override
    public String glitchedWebUser() {
        return USERS_CONFIG.glitchedUser();
    }

    @Override
    public String webPassword() {
        return USERS_CONFIG.password();
    }
}
