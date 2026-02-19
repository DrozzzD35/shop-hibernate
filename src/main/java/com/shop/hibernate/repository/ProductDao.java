package com.shop.hibernate.repository;

import com.shop.hibernate.model.Category;
import com.shop.hibernate.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductDao {

    Optional<Product> findProductById(long id);

    Product createProduct(Product product);

    void deleteProduct(Product product);

    List<Product> findAllProducts();

    Optional<Product> updateProduct(Product product);

    List<Product> findProductsByCategory(Category category);

    List<Product> findProductByPrice(BigDecimal price);

}
