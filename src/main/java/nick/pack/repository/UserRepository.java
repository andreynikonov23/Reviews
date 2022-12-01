package nick.pack.repository;

import nick.pack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

//    @Query("SELECT u FROM users u WHERE u.id = :id")
//    User findById(@Param("id") int id);
}
