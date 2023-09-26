package nick.pack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "ratings")
public class Rating {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private int rating;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "review")
    private Review review;


    public Rating(int rating, User user, Review review) {
        this.rating = rating;
        this.user = user;
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
