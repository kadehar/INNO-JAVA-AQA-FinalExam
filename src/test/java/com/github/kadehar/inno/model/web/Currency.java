package com.github.kadehar.inno.model.web;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Currency {
    USD("$");

    private final String sign;
}
