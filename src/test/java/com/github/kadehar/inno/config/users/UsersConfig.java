package com.github.kadehar.inno.config.users;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:users.properties",
        "file:~/users.properties",
        "file:./users.properties"
})
public interface UsersConfig extends Config {
    @Key("standard")
    String standardUser();
    @Key("glitch")
    String glitchedUser();
    @Key("password")
    String password();
}
