package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.Date;
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
public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idInvoice;

	float amountDiscount;
	float amountInvoice;

	@Temporal(TemporalType.DATE)
	Date dateCreationInvoice;

	@Temporal(TemporalType.DATE)
	Date dateLastModificationInvoice;

	Boolean archived;

	@OneToMany(mappedBy = "invoice")
	@JsonIgnore
	Set<InvoiceDetail> invoiceDetails; // Kept private and with @JsonIgnore for serialization

	@ManyToOne
	@JsonIgnore
	Supplier supplier;

	// Add the operator field with @ManyToOne annotation to establish the relationship with Operator
	@ManyToOne
	@JoinColumn(name = "operator_id")  // Foreign key column in Invoice table
	@JsonIgnore  // Prevents serialization issues with JSON
			Operator operator;  // Operator field to establish the relationship to the Operator entity
}
