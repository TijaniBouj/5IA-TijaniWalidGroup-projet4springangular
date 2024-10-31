package tn.esprit.devops_project.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
public class Supplier implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long idSupplier;

	String code;
	String label;

	@Enumerated(EnumType.STRING)
	SupplierCategory supplierCategory;

	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true) // Optional: cascade and orphan removal
	@JsonIgnore
	List<Invoice> invoices = new ArrayList<>(); // Changed to ArrayList for List type

	// Method to add an invoice to the supplier
	public void addInvoice(Invoice invoice) {
		invoices.add(invoice);
		invoice.setSupplier(this); // Ensure the invoice references this supplier
	}

	// Method to remove an invoice from the supplier
	public void removeInvoice(Invoice invoice) {
		invoices.remove(invoice);
		invoice.setSupplier(null); // Clear the reference in the invoice
	}
}
