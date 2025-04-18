package com.github.kadehar.inno.jupiter.annotation;

import com.github.kadehar.inno.jupiter.extension.TearDownEmployeeExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ExtendWith(TearDownEmployeeExtension.class)
public @interface TearDownEmployee {
}
