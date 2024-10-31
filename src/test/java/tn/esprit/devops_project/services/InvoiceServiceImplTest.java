package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InvoiceServiceImplTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRetrieveAllInvoices() {
        // Arrange
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        List<Invoice> invoices = Arrays.asList(invoice1, invoice2);
        when(invoiceRepository.findAll()).thenReturn(invoices);

        // Act
        List<Invoice> result = invoiceService.retrieveAllInvoices();

        // Assert
        assertEquals(2, result.size());
        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    void testCancelInvoice() {
        // Arrange
        Invoice invoice = new Invoice();
        invoice.setArchived(false);
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        // Act
        invoiceService.cancelInvoice(1L);

        // Assert
        assertTrue(invoice.isArchived());
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    void testRetrieveInvoice() {
        // Arrange
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));

        // Act
        Invoice result = invoiceService.retrieveInvoice(1L);

        // Assert
        assertNotNull(result);
        verify(invoiceRepository, times(1)).findById(1L);
    }

    @Test
    void testGetInvoicesBySupplier() {
        // Arrange
        Supplier supplier = new Supplier();
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        List<Invoice> invoices = new ArrayList<>(Arrays.asList(invoice1, invoice2)); // Use List
        supplier.setInvoices(invoices); // Assuming you have a setter for invoicesmvn
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.of(supplier));

        // Act
        List<Invoice> result = invoiceService.getInvoicesBySupplier(1L);

        // Assert
        assertEquals(2, result.size());
        verify(supplierRepository, times(1)).findById(1L);
    }

    @Test
    void testAssignOperatorToInvoice() {
        // Arrange
        Operator operator = new Operator();
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.of(invoice));
        when(operatorRepository.findById(anyLong())).thenReturn(Optional.of(operator));

        // Act
        invoiceService.assignOperatorToInvoice(1L, 1L);

        // Assert
        assertTrue(operator.getInvoices().contains(invoice));
        verify(operatorRepository, times(1)).save(operator);
        verify(invoiceRepository, times(1)).findById(1L);
        verify(operatorRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTotalAmountInvoiceBetweenDates() {
        // Arrange
        Date startDate = new Date();
        Date endDate = new Date();
        float expectedAmount = 500.0f;
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(expectedAmount);

        // Act
        float result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        // Assert
        assertEquals(expectedAmount, result);
        verify(invoiceRepository, times(1)).getTotalAmountInvoiceBetweenDates(startDate, endDate);
    }

    @Test
    void testCancelInvoice_NotFound() {
        // Arrange
        when(invoiceRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> invoiceService.cancelInvoice(1L));
        verify(invoiceRepository, times(1)).findById(1L);
        verify(invoiceRepository, never()).save(any());
    }

    @Test
    void testGetInvoicesBySupplier_NotFound() {
        // Arrange
        when(supplierRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> invoiceService.getInvoicesBySupplier(1L));
        verify(supplierRepository, times(1)).findById(1L);
    }
}
