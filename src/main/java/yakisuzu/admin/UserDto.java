package yakisuzu.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor
@Getter
class UserDto {
    @NonNull
    private String mail;
}
