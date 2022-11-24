package nick.pack.model;

import lombok.Data;

import javax.persistence.*;
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
}
