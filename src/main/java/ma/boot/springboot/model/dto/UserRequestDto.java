package ma.boot.springboot.model.dto;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.boot.springboot.validation.PasswordMatchConstraint;

@Data
@NoArgsConstructor
@PasswordMatchConstraint(
        password = "password",
        repeatPassword = "repeatPassword",
        message = "Password and repeat password are not the same!"
)
public class UserRequestDto {
    private String userId;
    private String profileName;
    @NotNull(message = "password can't be null")
    @Size(min = 8, message = "password should have at least 8 characters")
    private String password;
    @NotNull(message = "repeat password can't be null")
    @Size(min = 8, message = "password should have at least 8 characters")
    private String repeatPassword;

    public UserRequestDto(String userId, String profileName) {
        this.userId = userId;
        this.profileName = profileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o.getClass().equals(UserRequestDto.class))) {
            return false;
        }
        UserRequestDto that = (UserRequestDto) o;
        return getUserId().equals(that.getUserId())
                && getProfileName().equals(that.getProfileName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getProfileName());
    }

    @Override
    public String toString() {
        return "UserRequestDto { "
                + "userId = '" + userId
                + ", profileName = '" + profileName + " '}";
    }
}
