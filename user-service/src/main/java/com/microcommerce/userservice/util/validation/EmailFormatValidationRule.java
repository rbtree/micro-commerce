package com.microcommerce.userservice.util.validation;

import com.microcommerce.common.validation.ValidationResult;
import com.microcommerce.common.validation.ValidationRule;
import com.microcommerce.userservice.data.dto.RegistrationRequest;
import org.springframework.stereotype.Component;

@Component
public class EmailFormatValidationRule implements ValidationRule<RegistrationRequest> {

    /**
     * RFC 5322 email standard regex.
     */
    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Override
    public ValidationResult validate(RegistrationRequest registrationRequest, ValidationResult validationResult) {
        if (!registrationRequest.getEmail().matches(EMAIL_REGEX)) {
            validationResult.addErrorMessage("Invalid email", "Provided email address isn't valid.");
        }

        return validationResult;
    }
}
