package nick.pack.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "login")
    @Size(max = 25, message = "*Логин содержит больше 25 символов")
    @NotEmpty(message = "*Пустое поле")
    private String login;
    @Column (name = "password")
    @NotEmpty(message = "*Пустое поле")
    private String password;
    @Column (name = "name")
    @Size(max = 25, message = "*Имя не должно быть больше 25 символов")
    @NotEmpty(message = "*Пустое поле")
    private String name;
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

    public User(String login, String password, String name, String photo) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.photo = photo;
    }

    public User(String login, String password, String name, String photo, Role role, Status status) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.photo = photo;
        this.role = role;
        this.status = status;
    }

    @Override
    public String toString(){
        return String.format("User: [%d, %s, %s, %s, %s, %s, %s]", id, login, password, name, photo, role, status);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, login, password, name, photo, role, status);
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
}
