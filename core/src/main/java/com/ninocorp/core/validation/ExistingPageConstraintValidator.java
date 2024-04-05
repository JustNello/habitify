package com.ninocorp.core.validation;

import com.ninocorp.core.service.NotebooksManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExistingPageConstraintValidator implements ConstraintValidator<ExistingPage, String> {

    private final NotebooksManager notebooksManager;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return true;
    }
}
