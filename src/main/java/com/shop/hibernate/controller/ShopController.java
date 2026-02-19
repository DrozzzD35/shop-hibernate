package com.shop.hibernate.controller;

import com.shop.hibernate.dto.CategoryProductsDto;
import com.shop.hibernate.model.Category;
import com.shop.hibernate.model.Product;
import com.shop.hibernate.service.ShopService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ShopController {
    private final ShopService service;

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return service.createProduct(product);
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable long id) {
        return service.getProduct(id);
    }

    @DeleteMapping("/products/{id}")
    public void removeProduct(@PathVariable long id) {
        service.removeProduct(id);
    }

    @GetMapping("/products")
    public List<Product> findAllProducts() {
        return service.findAllProducts();
    }

    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product) {
        return service.updateProduct(product);
    }

    @GetMapping("/category/products")
    public List<Product> findProductsByCategory(@RequestBody Category category) {
        return service.findProductsByCategory(category);
    }

    @GetMapping("/products/price")
    public List<Product> findProductByPrice(@RequestBody BigDecimal price) {
        return service.findProductByPrice(price);
    }

    @GetMapping("/category/{id}")
    public Category findCategoryById(@PathVariable long id) {
        return service.findCategoryById(id);
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return service.createCategory(category);
    }

    @DeleteMapping("/category/{id}")
    public void removeCategory(@PathVariable long id) {
        service.removeCategory(id);
    }

    @GetMapping("/category")
    public List<Category> findAllCategories() {
        return service.findAllCategories();
    }

    @PutMapping("/category")
    public Category updateCategory(@RequestBody Category category) {
        return service.updateCategory(category);
    }

    @PostMapping("/category")
    @ResponseStatus(HttpStatus.CREATED)
    public Category createCategoryWithProducts(CategoryProductsDto categoryProductsDto) {
        Category category = categoryProductsDto.getCategory();
        List<Product> products = categoryProductsDto.getProducts();

        return service.createCategoryWithProducts(category, products);

    }


}
