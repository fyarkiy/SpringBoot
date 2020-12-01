package ma.boot.springboot.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = PasswordMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatchConstraint {
    String message() default "Passwords are not the same";

    String password();

    String repeatPassword();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
