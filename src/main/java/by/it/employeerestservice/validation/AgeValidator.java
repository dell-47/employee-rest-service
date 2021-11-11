package by.it.employeerestservice.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.Period;
import java.sql.Date;

public class AgeValidator implements ConstraintValidator<AgeValid, Date> {
    @Override
    public boolean isValid(Date dateToValidate, ConstraintValidatorContext constraintValidatorContext) {
        final int VALID_AGE = 18;
        return Period.between(dateToValidate.toLocalDate(), LocalDate.now()).getYears() >= VALID_AGE;
    }
}
