package tn.esprit.devops_project.controllers;

import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.services.Iservices.IInvoiceService;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class InvoiceController {

    private static final Logger logger = LogManager.getLogger(InvoiceController.class);
    private final IInvoiceService invoiceService;

    @GetMapping("/invoice")
    public List<Invoice> getInvoices() {
        logger.info("Fetching all invoices");
        return invoiceService.retrieveAllInvoices();
    }

    @GetMapping("/invoice/{invoiceId}")
    public Invoice retrieveInvoice(@PathVariable Long invoiceId) {
        logger.info("Retrieving invoice with ID: {}", invoiceId);
        return invoiceService.retrieveInvoice(invoiceId);
    }

    @PutMapping("/invoice/{invoiceId}")
    public void cancelInvoice(@PathVariable Long invoiceId) {
        logger.warn("Cancelling invoice with ID: {}", invoiceId);
        invoiceService.cancelInvoice(invoiceId);
    }

    @GetMapping("/invoice/supplier/{supplierId}")
    public List<Invoice> getInvoicesBySupplier(@PathVariable Long supplierId) {
        logger.info("Fetching invoices for supplier with ID: {}", supplierId);
        return invoiceService.getInvoicesBySupplier(supplierId);
    }

    @PutMapping(value = "/invoice/operator/{idOperator}/{idInvoice}")
    public void assignOperatorToInvoice(@PathVariable Long idOperator, @PathVariable Long idInvoice) {
        logger.info("Assigning operator with ID: {} to invoice with ID: {}", idOperator, idInvoice);
        invoiceService.assignOperatorToInvoice(idOperator, idInvoice);
    }

    @GetMapping("/invoice/price/{startDate}/{endDate}")
    public float getTotalAmountInvoiceBetweenDates(@PathVariable Date startDate, @PathVariable Date endDate) {
        logger.info("Calculating total invoice amount between dates: {} and {}", startDate, endDate);
        return invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);
    }
}
