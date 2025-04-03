package com.github.kadehar.inno.config.api;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:api.properties",
        "file:~/api.properties",
        "file:./api.properties"
})
public interface ApiConfig extends Config {
    @Key("baseUrl")
    String url();
}
