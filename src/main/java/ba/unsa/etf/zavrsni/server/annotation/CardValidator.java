package ba.unsa.etf.zavrsni.server.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CardValidator implements ConstraintValidator<CardValidation, String> {
    @Override
    public void initialize(CardValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String cardNumber, ConstraintValidatorContext constraintValidatorContext) {
        return cardNumber.matches("^[0-9]*$");
    }
}
