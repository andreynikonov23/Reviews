package nick.pack.repository;

import nick.pack.model.Comment;
import nick.pack.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r FROM Role r WHERE r.id = :id")
    Role findRoleById(@Param("id") int id);
}
