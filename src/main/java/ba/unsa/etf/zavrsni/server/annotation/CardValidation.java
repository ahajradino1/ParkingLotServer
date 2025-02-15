package ba.unsa.etf.zavrsni.server.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CardValidator.class)
public @interface CardValidation {
    String message();
    Class<?>[] groups() default {};
    Class<? extends Payload> [] payload() default {};
}

