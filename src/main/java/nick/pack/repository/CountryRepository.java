package nick.pack.repository;

import nick.pack.model.Comment;
import nick.pack.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query("SELECT c FROM Country c WHERE c.id = :id")
    Country findCountryById(@Param("id") int id);
}
