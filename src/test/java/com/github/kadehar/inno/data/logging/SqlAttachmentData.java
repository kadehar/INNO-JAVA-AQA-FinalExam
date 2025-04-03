package com.github.kadehar.inno.data.logging;

import io.qameta.allure.attachment.AttachmentData;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SqlAttachmentData implements AttachmentData {
    private final String name;
    private final String sql;
}
