package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Operator implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idOperateur;

	String fname;
	String lname;
	String password;

	@OneToMany(mappedBy = "operator") // Add mappedBy to define the inverse side of the relationship
	@JsonIgnore // Prevents serialization issues with JSON
	Set<Invoice> invoices;

	// Optional: Override toString, equals, and hashCode if needed
}
