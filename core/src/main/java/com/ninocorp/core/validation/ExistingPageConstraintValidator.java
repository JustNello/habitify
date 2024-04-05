package com.ninocorp.core.validation;

import com.ninocorp.core.service.NotebooksManager;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import static com.ninocorp.core.util.StringUtil.isEmpty;
import static java.lang.String.format;

@RequiredArgsConstructor
public class ExistingPageConstraintValidator implements ConstraintValidator<ExistingPage, String> {

    private final NotebooksManager notebooksManager;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        // disable default message
        context.disableDefaultConstraintViolation();

        // customize message
        if (!isExistingPage(value))
            context.buildConstraintViolationWithTemplate(
                            format("There is no page with the given name: [%s] This is a list of available pages: %s",
                                    value, notebooksManager.mostUsedNotebook().getPages().keys()))
                    .addConstraintViolation();

        return isExistingPage(value);
    }

    private boolean isExistingPage(String value) {
        return isEmpty(value) || notebooksManager.mostUsedNotebook().getPages().containsItem(value);
    }
}
