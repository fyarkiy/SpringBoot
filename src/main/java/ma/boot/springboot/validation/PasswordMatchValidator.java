package ma.boot.springboot.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import ma.boot.springboot.model.dto.UserRequestDto;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordMatchValidator
        implements ConstraintValidator<PasswordMatchConstraint, UserRequestDto> {
    private String password;
    private String repeatPassword;

    @Override
    public void initialize(PasswordMatchConstraint constraint) {
        this.password = constraint.password();
        this.repeatPassword = constraint.repeatPassword();
    }

    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        String passwordValue = (String) new BeanWrapperImpl(userRequestDto)
                .getPropertyValue(password);
        String repeatPasswordValue = (String) new BeanWrapperImpl(userRequestDto)
                .getPropertyValue(repeatPassword);

        return passwordValue != null && passwordValue.equals(repeatPasswordValue);
    }
}
