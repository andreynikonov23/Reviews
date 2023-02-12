package nick.pack.repository;

import nick.pack.model.ConfirmUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConfirmUserRepository extends JpaRepository<ConfirmUser, Integer> {
    @Query ("SELECT c FROM ConfirmUser c WHERE id = :id")
    ConfirmUser findConfirmUserById(@Param("id") int id);
}
