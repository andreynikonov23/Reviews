package nick.pack.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
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
    @Column (name = "cast_names")
    private String cast;
    @Column (name = "text")
    private String text;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    private User user;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "country_id")
    private Country country;

    @OneToMany (mappedBy = "review")
    Set<Comment> commentSet;
    @OneToMany (mappedBy = "review")
    Set<Rating> ratingSet;

    public Review(String name, String trailerUrl, String poster, String filmName, int year, String director, String cast, String text, User user, Country country) {
        this.name = name;
        this.trailerUrl = trailerUrl;
        this.poster = poster;
        this.filmName = filmName;
        this.year = year;
        this.director = director;
        this.cast = cast;
        this.text = text;
        this.user = user;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public String toString(){
        return String.format("Review: %d, %s, %s, %s, %s, %s, [%s]", id, name, filmName, user, trailerUrl, poster, user);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review = (Review) o;
        return id == review.id && year == review.year && name.equals(review.name) && trailerUrl.equals(review.trailerUrl) && poster.equals(review.poster) && filmName.equals(review.filmName) && director.equals(review.director) && cast.equals(review.cast) && text.equals(review.text) && user.equals(review.user) && country.equals(review.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, trailerUrl, poster, filmName, year, director, cast, text, user, country);
    }
}
