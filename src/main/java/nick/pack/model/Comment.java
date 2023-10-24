package nick.pack.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "comments")
public class Comment{
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "comment")
    private String comment;
    @Column (name = "date")
    private LocalDateTime date;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "answer")
    private Comment answer;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "review_id")
    private Review review;

    public Comment(String comment, LocalDateTime date, User user, Review review) {
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.review = review;
    }

    public Comment(int id, String comment, LocalDateTime date, User user, Comment answer, Review review) {
        this.id = id;
        this.comment = comment;
        this.date = date;
        this.user = user;
        this.answer = answer;
        this.review = review;
    }

    public Comment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Comment getAnswer() {
        return answer;
    }

    public void setAnswer(Comment answer) {
        this.answer = answer;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public String toString(){
        return String.format("Comment: %d, %s, %s, %s, %d", id, user.getLogin(), comment, answer, review.getId());
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
