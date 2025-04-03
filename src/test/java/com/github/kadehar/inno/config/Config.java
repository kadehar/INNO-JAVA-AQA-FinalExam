package com.github.kadehar.inno.config;

public interface Config {
    static Config getInstance() {
        return LocalConfig.INSTANCE;
    }

    String dbUrl();
    String dbLogin();
    String dbPassword();
    String dbUserRole();
    String dbUserName();
    String dbUserPassword();
    String apiBaseUrl();
    String webBaseUrl();
    String browser();
    String pageLoadStrategy();
    Boolean headless();
    Long timeout();
    Boolean enableSteps();
    String standardWebUser();
    String glitchedWebUser();
    String webPassword();
}
