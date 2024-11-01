package tn.esprit.devops_project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.entities.Product;
import tn.esprit.devops_project.entities.ProductCategory;
import tn.esprit.devops_project.services.Iservices.IProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/product") // Grouping product-related endpoints
public class ProductController {

    private final IProductService productService;

    @CrossOrigin(origins = "https://trusted-domain.com") // Replace with your actual domain
    @PostMapping("/{idStock}")
    public Product addProduct(@RequestBody Product product, @PathVariable Long idStock) {
        return productService.addProduct(product, idStock);
    }

    @CrossOrigin(origins = "https://trusted-domain.com") // Replace with your actual domain
    @GetMapping("/{id}")
    public Product retrieveProduct(@PathVariable Long id) {
        return productService.retrieveProduct(id);
    }

    @CrossOrigin(origins = "https://trusted-domain.com") // Replace with your actual domain
    @GetMapping
    public List<Product> retrieveAllProducts() {
        return productService.retreiveAllProduct();
    }

    @CrossOrigin(origins = "https://trusted-domain.com") // Replace with your actual domain
    @GetMapping("/stock/{id}")
    public List<Product> retrieveProductStock(@PathVariable Long id) {
        return productService.retreiveProductStock(id);
    }

    @CrossOrigin(origins = "https://trusted-domain.com") // Replace with your actual domain
    @GetMapping("/category/{category}")
    public List<Product> retrieveProductByCategory(@PathVariable ProductCategory category) {
        return productService.retrieveProductByCategory(category);
    }

    @CrossOrigin(origins = "https://trusted-domain.com") // Replace with your actual domain
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
