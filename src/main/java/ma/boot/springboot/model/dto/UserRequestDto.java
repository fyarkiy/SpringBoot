package ma.boot.springboot.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserRequestDto {
    private String userId;
    private String profileName;
    private String password;

    public UserRequestDto(String userId, String profileName) {
        this.userId = userId;
        this.profileName = profileName;
    }
}
