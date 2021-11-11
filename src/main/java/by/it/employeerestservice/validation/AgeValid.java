package by.it.employeerestservice.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeValidator.class)
public @interface AgeValid {
    public String message() default "Must be at least 18 years old";
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
}
