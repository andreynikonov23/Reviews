package nick.pack.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@Table (name = "country")
public class Country {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "country_name")
    private String countryName;

    @OneToMany (mappedBy = "country")
    Set<Review> reviewSet;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString(){
        return String.format("Country: %d, %s", id, countryName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id && countryName.equals(country.countryName);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, countryName);
    }
}
