package com.ninocorp.core.validation;

import com.ninocorp.core.AbstractSpringBootTest;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
    void validate() {
        validator.validate(new Foo("Hello"));
    }
}
