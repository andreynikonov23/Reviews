package nick.pack.model;

import lombok.Data;

import javax.persistence.*;
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
}
