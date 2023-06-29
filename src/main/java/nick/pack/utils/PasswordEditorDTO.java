package nick.pack.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nick.pack.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordEditorDTO {
    private User user;
    private String password;
}
