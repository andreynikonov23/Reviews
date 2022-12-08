package nick.pack.repository;

import nick.pack.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StatusRepository extends JpaRepository<Status, Integer> {
    @Query("SELECT s FROM Status s WHERE s.id = :id")
    Status findStatusById(@Param("id") int id);
}
