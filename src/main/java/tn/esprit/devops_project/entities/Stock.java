package tn.esprit.devops_project.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idStock; // Use "Long" instead of "long" to allow null values.

    String title;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Added cascade and fetch strategies
    Set<Product> products;

    // Optional: Override toString(), equals(), and hashCode() for better logging and comparison
    @Override
    public String toString() {
        return "Stock{" +
                "idStock=" + idStock +
                ", title='" + title + '\'' +
                '}';
    }
}
