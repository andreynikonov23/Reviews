package nick.pack.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table (name = "role")
public class Role {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated (value = EnumType.STRING)
    @Column (name = "name")
    private RoleEnum name;

    @OneToMany(mappedBy = "role")
    Set<User> userSet;

    public Role(RoleEnum name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name.toString();
    }

    @Override
    public int hashCode(){
        return Objects.hash(id, name);
    }
    @Override
    public boolean equals(Object obj){
        if (obj == null){
            return false;
        }
        if (!(obj instanceof User)){
            return false;
        }
        if (this.getClass() != obj.getClass()){
            return false;
        }
        if (this == obj){
            return true;
        }
        Role roleObj = (Role) obj;
        if (roleObj.hashCode() == hashCode()){
            return id == roleObj.getId() && name.equals(roleObj.getName());
        }
        return false;
    }
}
