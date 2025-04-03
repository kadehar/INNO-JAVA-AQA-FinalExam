package com.github.kadehar.inno.config.db;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:db.properties",
        "file:~/db.properties",
        "file:./db.properties"
})
public interface DbConfig extends Config {
    @Key("dbUrl")
    String url();
    @Key("dbUser")
    String login();
    @Key("dbPassword")
    String password();
    @Key("userName")
    String userLogin();
    @Key("userPassword")
    String userPassword();
    @Key("userRole")
    String role();
}
