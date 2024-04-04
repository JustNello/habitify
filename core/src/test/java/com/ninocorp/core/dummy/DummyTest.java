package com.ninocorp.core.dummy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest("dummy.message=Hello")
class DummyTest {

    @Autowired
    private DummyService dummyService;

    @Test
    public void contextLoads() {
        Assertions.assertEquals("Hello", dummyService.message());
    }

    @SpringBootApplication
    static class TestConfiguration {

    }
}
