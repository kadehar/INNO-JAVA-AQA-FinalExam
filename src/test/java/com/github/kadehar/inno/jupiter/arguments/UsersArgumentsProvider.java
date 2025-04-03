package com.github.kadehar.inno.jupiter.arguments;

import com.github.kadehar.inno.config.Config;
import com.github.kadehar.inno.model.web.User;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UsersArgumentsProvider implements ArgumentsProvider {

    private static final Config CFG = Config.getInstance();

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        Named.of(
                                "Login as " + CFG.standardWebUser(),
                                new User(CFG.standardWebUser(), CFG.webPassword())
                        )
                ),
                Arguments.of(
                        Named.of(
                                "Login as " + CFG.glitchedWebUser(),
                                new User(CFG.glitchedWebUser(), CFG.webPassword())
                        )
                )
        );
    }
}
