package nick.pack.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table (name = "confirm_user")
public class ConfirmUser {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "is_confirm")
    @Enumerated (value = EnumType.STRING)
    private ConfirmUserEnum isConfirm;

    @OneToMany(mappedBy = "confirmUser")
    Set<User> userSet;

    public ConfirmUser (ConfirmUserEnum isConfirm){
        this.isConfirm = isConfirm;
    }

    @Override
    public String toString(){
        return isConfirm.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfirmUser that = (ConfirmUser) o;
        return id == that.id && isConfirm == that.isConfirm && Objects.equals(userSet, that.userSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isConfirm, userSet);
    }
}
