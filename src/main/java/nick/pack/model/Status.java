package nick.pack.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "status")
    private String status;

    @OneToMany(mappedBy = "status")
    Set<User> userSet;
}
