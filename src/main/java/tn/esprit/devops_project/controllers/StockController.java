package tn.esprit.devops_project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.entities.Stock;
import tn.esprit.devops_project.services.Iservices.IStockService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
public class StockController {

    private final IStockService stockService;
    private static final Logger logger = LogManager.getLogger(StockController.class);

    @PostMapping("/stock")
    public Stock addStock(@RequestBody Stock stock) {
        logger.info("Adding stock: {}", stock);
        return stockService.addStock(stock);
    }

    @GetMapping("/stock/{id}")
    public Stock retrieveStock(@PathVariable Long id) {
        logger.info("Retrieving stock with id: {}", id);
        return stockService.retrieveStock(id);
    }

    @GetMapping("/stock")
    public List<Stock> retrieveAllStock() {
        logger.info("Retrieving all stocks");
        return stockService.retrieveAllStock();
    }
}
