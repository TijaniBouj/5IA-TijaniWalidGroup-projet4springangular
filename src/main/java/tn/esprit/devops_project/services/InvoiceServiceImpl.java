package tn.esprit.devops_project.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;
import tn.esprit.devops_project.exceptions.ResourceNotFoundException; // Custom exception

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class InvoiceServiceImpl implements IInvoiceService {

	final InvoiceRepository invoiceRepository;
	final OperatorRepository operatorRepository;
	final InvoiceDetailRepository invoiceDetailRepository;
	final SupplierRepository supplierRepository;

	@Override
	public List<Invoice> retrieveAllInvoices() {
		log.info("Retrieving all invoices");
		return invoiceRepository.findAll();
	}

	@Override
	public void cancelInvoice(Long invoiceId) {
		log.info("Cancelling invoice with ID: {}", invoiceId);
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + invoiceId));

		invoice.setArchived(true);
		invoiceRepository.save(invoice);

		// Assuming this method does additional work
		invoiceRepository.updateInvoice(invoiceId);
		log.info("Invoice with ID: {} has been cancelled", invoiceId);
	}

	@Override
	public Invoice retrieveInvoice(Long invoiceId) {
		log.info("Retrieving invoice with ID: {}", invoiceId);
		return invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + invoiceId));
	}

	@Override
	public List<Invoice> getInvoicesBySupplier(Long idSupplier) {
		log.info("Retrieving invoices for supplier with ID: {}", idSupplier);
		Supplier supplier = supplierRepository.findById(idSupplier)
				.orElseThrow(() -> new ResourceNotFoundException("Supplier not found with ID: " + idSupplier));
		return (List<Invoice>) supplier.getInvoices();
	}

	@Override
	public void assignOperatorToInvoice(Long idOperator, Long idInvoice) {
		log.info("Assigning operator with ID: {} to invoice with ID: {}", idOperator, idInvoice);
		Invoice invoice = invoiceRepository.findById(idInvoice)
				.orElseThrow(() -> new ResourceNotFoundException("Invoice not found with ID: " + idInvoice));
		Operator operator = operatorRepository.findById(idOperator)
				.orElseThrow(() -> new ResourceNotFoundException("Operator not found with ID: " + idOperator));

		operator.getInvoices().add(invoice);
		operatorRepository.save(operator);
		log.info("Operator with ID: {} has been assigned to invoice with ID: {}", idOperator, idInvoice);
	}

	@Override
	public float getTotalAmountInvoiceBetweenDates(Date startDate, Date endDate) {
		log.info("Calculating total amount of invoices between dates: {} and {}", startDate, endDate);
		return invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate);
	}
}
