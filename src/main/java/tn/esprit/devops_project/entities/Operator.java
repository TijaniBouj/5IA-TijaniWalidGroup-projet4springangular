package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

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

	@OneToMany(mappedBy = "operator") // Establish bidirectional relationship with Invoice
	@JsonIgnore
	Set<Invoice> invoices = new HashSet<>(); // Initialize to avoid NullPointerException


}
