package com.ninocorp.shell.portable;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("habits")
@Getter
@Setter
@Validated
public class StorageProperties {

    @NotNull
    private String file;
}
