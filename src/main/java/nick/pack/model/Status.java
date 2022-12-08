package nick.pack.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
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

    public Status(String status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status1 = (Status) o;
        return id == status1.id && status.equals(status1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
