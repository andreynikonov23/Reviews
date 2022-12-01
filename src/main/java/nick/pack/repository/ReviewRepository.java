package nick.pack.repository;

import nick.pack.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
//    @Query("SELECT r FROM reviews r WHERE r.id = :id")
//    Review findReviewById(@Param("id") int id);
}
