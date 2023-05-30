package nick.pack.repository;

import nick.pack.model.Country;
import nick.pack.model.Review;
import nick.pack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    @Query("SELECT r FROM Review r WHERE r.id = :id")
    Review findReviewById(@Param("id") int id);

    @Query("SELECT r FROM Review r WHERE r.user = :user")
    List<Review> findReviewsByUser(@Param("user") User user);

//    @Query("UPDATE Review SET name = :name, trailerUrl = :trailerUrl, poster = :poster, filmName = :filmName, year = :year, director = :director, cast = :cast, text = :text, user = :user, country = :country WHERE id = :id")
//    void update (int id, @Param("name") String name, @Param("trailerUrl") String trailerUrl, @Param("poster") String poster, @Param("filmName") String filmName, @Param("year") int year, @Param("director") String director, @Param("cast") String cast, @Param("text") String text, @Param("user") User user, @Param("country")Country country);
}