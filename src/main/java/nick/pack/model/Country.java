package nick.pack.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString(){
        return countryName;
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
