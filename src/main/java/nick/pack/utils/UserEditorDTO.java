package nick.pack.utils;

import nick.pack.model.User;

public class UserEditorDTO {
    private User user;
    private String password;
    private String nick;
    private String photo;

    public UserEditorDTO(User user, String password, String nick, String photo) {
        this.user = user;
        this.password = password;
        this.nick = nick;
        this.photo = photo;
    }
    public UserEditorDTO(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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
