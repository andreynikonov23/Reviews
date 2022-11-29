package nick.pack.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "comment")
    private String comment;
    @Column (name = "date")
    private LocalDate date;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "answer")
    private User answer;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "review_id")
    private Review review;

    public Comment(String comment, User user, User answer, Review review) {
        this.comment = comment;
        this.user = user;
        this.answer = answer;
        this.review = review;
    }

    @Override
    public String toString(){
        return String.format("Comment: %d, %t, %d, %d, %d, %d", id, date, user, comment, answer, review);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment1 = (Comment) o;
        return id == comment1.id && comment.equals(comment1.comment) && date.equals(comment1.date) && user.equals(comment1.user) && answer.equals(comment1.answer) && review.equals(comment1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, comment, date, user, answer, review);
    }
}
