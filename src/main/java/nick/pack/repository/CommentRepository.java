package nick.pack.repository;

import nick.pack.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    @Query("SELECT c FROM comments c WHERE c.id = :id")
    Comment findById(@Param("id") int id);
}
