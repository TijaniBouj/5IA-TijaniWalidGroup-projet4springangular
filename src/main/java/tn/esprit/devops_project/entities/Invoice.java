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
	Set<InvoiceDetail> invoiceDetails;

	@ManyToOne
	@JsonIgnore
	Supplier supplier;

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}
}
