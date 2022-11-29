package nick.pack.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table (name = "users")
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "login")
    private String login;
    @Column (name = "password")
    private String password;
    @Column (name = "name")
    private String name;
    @Column (name = "photo")
    private String photo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role")
    private Role role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status")
    private Status status;

    @OneToMany(mappedBy = "user")
    Set<Review> reviewSet;
    @OneToMany(mappedBy = "user")
    Set<Comment> commentSet;

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
