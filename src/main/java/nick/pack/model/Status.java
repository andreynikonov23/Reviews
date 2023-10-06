package nick.pack.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "status")
public class Status {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated (value = EnumType.STRING)
    @Column (name = "status")
    private StatusEnum status;

    @OneToMany(mappedBy = "status")
    Set<User> userSet;

    public Status(StatusEnum status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusEnum getStatusEnum() {
        return status;
    }

    public void setStatusEnum(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return status.toString();
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
