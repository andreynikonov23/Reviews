package nick.pack.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nick.pack.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEditorDTO {
    private User user;
    private String password;
    private String nick;
    private String photo;

    public UserEditorDTO(User user, String password){
        this.user = user;
        this.password = password;
    }

    public UserEditorDTO(User user) {
        this.user = user;
        this.nick = user.getNick();
        this.photo = user.getPhoto();
    }
}
