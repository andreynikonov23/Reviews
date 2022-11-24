package nick.pack.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "name")
    private String name;
    @Column (name = "trailer_url")
    private String trailerUrl;
    @Column (name = "poster")
    private String poster;
    @Column (name = "film_name")
    private String filmName;
    @Column (name = "year")
    private int year;
    @Column (name = "director")
    private String director;
    @Column (name = "cast")
    private String cast;
    @Column (name = "rating")
    private double rating;
    @Column (name = "text")
    private String text;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "country")
    private Country country;

    @OneToMany (mappedBy = "review")
    Set<Comment> commentSet;
}
