package nick.pack.model;

import nick.pack.service.RatingService;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "login")
    @Size(max = 25, message = "*Логин содержит больше 25 символов")
    @NotEmpty(message = "*Пустое поле - Логин")
    private String login;

    @Column (name = "password")
    @NotEmpty(message = "*Пустое поле - Пароль")
    private String password;

    @Column (name = "nickname")
    @Size(max = 25, message = "*Имя не должно быть больше 25 символов")
    @NotEmpty(message = "*Пустое поле Имя")
    private String nick;

    @Column (name = "email")
    @Email(message = "Недопустимый Email")
    @NotEmpty(message = "*Пустое поле Email")
    private String email;

    @Column (name = "photo")
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private Status status;


    @OneToMany(mappedBy = "user")
    Set<Review> reviewSet;
    @OneToMany(mappedBy = "user")
    Set<Comment> commentSet;
    @OneToMany(mappedBy = "user")
    Set<Rating> ratingSet;


    public User(){}

    public User(int id, String login, String password, String nick, String email, String photo, Role role, Status status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.nick = nick;
        this.email = email;
        this.photo = photo;
        this.role = role;
        this.status = status;
    }

    public User(String login, String password, String nick, String email, String photo) {
        this.login = login;
        this.password = password;
        this.nick = nick;
        this.email = email;
        this.photo = photo;
    }

    public User(String login, String password, String nick, String email, String photo, Role role, Status status) {
        this.login = login;
        this.password = password;
        this.nick = nick;
        this.email = email;
        this.photo = photo;
        this.role = role;
        this.status = status;
    }

    public String getNick() {
        return nick;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return String.format("User: [%d, %s, %s, %s, %s, %s, %s, %s]", id, login, password, nick, email, photo, role, status);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, login, password, nick, email, photo, role, status);
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (!(obj instanceof User)){
            return false;
        }
        if (obj.getClass() != this.getClass()){
            return false;
        }
        if (this == obj){
            return true;
        }
        User user = (User) obj;
        if (user.hashCode() == hashCode()){
            return id == user.getId() && login.equals(user.login) && password.equals(user.getPassword());
        }
        return false;
    }

    public boolean isAdmin(){
        if (this.role.getRoleName().equals(RoleEnum.ADMIN)){
            return true;
        }
        return false;
    }
    public boolean isActive() {
        if (this.status.getStatusEnum().equals(StatusEnum.ACTIVE)) {
            return true;
        }
        return false;
    }
}
