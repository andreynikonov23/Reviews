package nick.pack.repository;

import nick.pack.model.Comment;
import nick.pack.model.Review;
import nick.pack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM Comment c WHERE c.id = :id")
    Comment findCommentById(@Param("id") int id);

    @Query("SELECT c FROM Comment c WHERE c.review = :review")
    List<Comment> findCommentsReview(@Param("review")Review review);

    @Query("SELECT c FROM Comment c WHERE c.comment = :comment AND c.date = :date AND c.user = :user AND c.answer = :answer AND c.review = :review")
    Comment getCommentId (@Param("comment") String comment, @Param("date") LocalDateTime date, @Param("user") User user, @Param("answer") Comment answer, @Param("review") Review review);
}
