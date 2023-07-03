package nick.pack.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nick.pack.model.User;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileEditorDTO {
    private String login;

    @NotEmpty(message = "*Пустое поле")
    private String nick;

    private String photo;

}
