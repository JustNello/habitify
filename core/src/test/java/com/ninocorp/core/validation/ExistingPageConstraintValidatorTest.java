package com.ninocorp.core.validation;

import com.ninocorp.core.AbstractSpringBootTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

class ExistingPageConstraintValidatorTest extends AbstractSpringBootTest {

    static class Foo {
        @ExistingPage
        String userPage;

        public Foo(String userPage) {
            this.userPage = userPage;
        }
    }

    @Autowired
    Validator validator;

    @Test
    void invalid() {
        Set<ConstraintViolation<Foo>> result = validator.validate(new Foo("Hello"));

        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals(
                "There is no page with the given name: [Hello] This is a list of available pages: [Exercise]",
                result.iterator().next().getMessage());
    }

    @Test
    void valid() {
        Set<ConstraintViolation<Foo>> result = validator.validate(new Foo("exerCise"));

        Assertions.assertEquals(0, result.size());
    }
}
