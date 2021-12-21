package examples.model;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EvenNumberValidator implements ConstraintValidator<Even, Integer> {
    boolean includesZero;

    public void initialize(Even constraint) {
        includesZero = constraint.includeZero();
    }

    public boolean isValid(Integer value, ConstraintValidatorContext ctx) {
        if (value == null)
            return true;
        if (value == 0)
            return includesZero;
        return value % 2 == 0;
    }
}
