package vn.edu.iuh.fit.lab7.frontend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import vn.edu.iuh.fit.lab7.backend.models.Product;
import vn.edu.iuh.fit.lab7.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.lab7.backend.services.ProductServices;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
public class ProductController {
    @Autowired
    private ProductServices productService;
    @GetMapping("/products")
    @ResponseBody
    public List<Product> findAllProducts(){
        return productService.findAll();
    }
    @GetMapping("/delete/{id}")
    public RedirectView deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return new RedirectView("/listproduct", true);    }

    @PostMapping("/save/{id}")
    public RedirectView saveProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Product product = productService.findProductbyID(id);
        product.setName(updatedProduct.getName());
        product.setDescription(updatedProduct.getDescription());
        product.setUnit(updatedProduct.getUnit());
        product.setManufacturer(updatedProduct.getManufacturer());
        product.setStatus(updatedProduct.getStatus());
        productService.save(product);
        return new RedirectView("/listproduct", true);    }

    @PostMapping("/add")
    public RedirectView addProduct(@RequestBody Product product) {
        productService.save(product);
        return new RedirectView("/listproduct", true);    }
}
