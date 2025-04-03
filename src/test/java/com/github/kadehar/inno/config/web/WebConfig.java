package com.github.kadehar.inno.config.web;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "classpath:web.properties",
        "file:~/web.properties",
        "file:./web.properties"
})
public interface WebConfig extends Config {
    @Key("browser")
    String browser();
    @Key("pageLoadStrategy")
    String pageLoadStrategy();
    @Key("baseUrl")
    String baseUrl();
    @Key("headless")
    Boolean headless();
    @Key("timeout")
    Long timeout();
    @Key("enableSteps")
    Boolean enableSteps();
}
