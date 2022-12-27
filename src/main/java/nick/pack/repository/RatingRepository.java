package nick.pack.repository;

import nick.pack.model.Rating;
import nick.pack.model.Review;
import nick.pack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Integer> {
    @Query("SELECT r FROM Rating r WHERE r.id = :id")
    Rating findRatingById(@Param("id") int id);

    @Query("SELECT r FROM Rating r WHERE r.review = :review")
    List<Rating> findRatingByReview(@Param("review") Review review);

    @Query("SELECT r FROM Rating r WHERE r.user = :user")
    List<Rating> findRatingByUser(@Param("user") User user);

}
