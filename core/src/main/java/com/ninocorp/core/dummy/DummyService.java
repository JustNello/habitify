package com.ninocorp.core.dummy;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
@EnableConfigurationProperties(DummyProperties.class)
@RequiredArgsConstructor
public class DummyService {

    private final DummyProperties dummyProperties;

    public String message() {
        return this.dummyProperties.getMessage();
    }

}
