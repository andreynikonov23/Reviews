package nick.pack.repository;

import nick.pack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    User findUserById(@Param("id") int id);
    @Query("SELECT u FROM User u WHERE u.login = :login")
    User returnUserObjByLogin(@Param("login") String login);
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findUserByEmail(@Param("email") String email);
}
