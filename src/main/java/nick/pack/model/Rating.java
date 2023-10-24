package nick.pack.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table (name = "ratings")
public class Rating {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private int rating;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "review")
    private Review review;

    public Rating(int id, int rating, User user, Review review) {
        this.id = id;
        this.rating = rating;
        this.user = user;
        this.review = review;
    }
    public Rating(){}

    public Rating(int rating, User user, Review review) {
        this.rating = rating;
        this.user = user;
        this.review = review;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    @Override
    public String toString(){
        return String.format("Rating[ %d, %s, %s, %d]", id, user, review, rating);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rating rating1 = (Rating) o;
        return id == rating1.id && rating == rating1.rating && user.equals(rating1.user) && review.equals(rating1.review);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rating, user, review);
    }
}
