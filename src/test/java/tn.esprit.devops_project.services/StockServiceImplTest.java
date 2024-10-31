package tn.esprit.devops_project.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.repositories.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @BeforeEach
    public void setUp() {
        // Initialize mocks before each test
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddStock() {
        // Given
        Stock stock = new Stock();
        stock.setIdStock(1L);  // Use setIdStock instead of setId
        stock.setTitle("Sample Stock");

        // When
        when(stockRepository.save(stock)).thenReturn(stock);

        // Then
        Stock savedStock = stockService.addStock(stock);
        assertEquals(stock.getIdStock(), savedStock.getIdStock());  // Use getIdStock
        verify(stockRepository, times(1)).save(stock); // Verify that save was called once
    }

    @Test
    public void testRetrieveStock() {
        // Given
        Stock stock = new Stock();
        stock.setIdStock(1L);  // Use setIdStock instead of setId
        stock.setTitle("Sample Stock");

        // When
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        // Then
        Stock retrievedStock = stockService.retrieveStock(1L);
        assertEquals(stock.getIdStock(), retrievedStock.getIdStock());  // Use getIdStock
        verify(stockRepository, times(1)).findById(1L); // Verify that findById was called once
    }

    @Test
    public void testRetrieveStockNotFound() {
        // Given
        when(stockRepository.findById(1L)).thenReturn(Optional.empty());

        // Then
        NullPointerException exception = assertThrows(NullPointerException.class, () -> {
            stockService.retrieveStock(1L);
        });
        assertEquals("Stock not found", exception.getMessage());
        verify(stockRepository, times(1)).findById(1L); // Verify that findById was called once
    }

    @Test
    public void testRetrieveAllStock() {
        // Given
        Stock stock1 = new Stock();
        Stock stock2 = new Stock();
        when(stockRepository.findAll()).thenReturn(List.of(stock1, stock2));

        // When
        List<Stock> stocks = stockService.retrieveAllStock();

        // Then
        assertEquals(2, stocks.size());
        verify(stockRepository, times(1)).findAll(); // Verify that findAll was called once
    }
}
