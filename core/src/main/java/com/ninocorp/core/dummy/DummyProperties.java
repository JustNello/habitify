package com.ninocorp.core.dummy;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("dummy")
@Setter
@Getter
public class DummyProperties {

    private String message;
}
