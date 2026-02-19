package com.shop.hibernate.repository;

import com.shop.hibernate.model.Category;
import com.shop.hibernate.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository implements ProductDao {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Optional<Product> findProductById(long id) {
        Product product = manager.find(Product.class, id);
        return Optional.ofNullable(product);
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getId() != 0) {
            throw new RuntimeException("Ошибка при создании товара");
        }

        manager.persist(product);
        return product;
    }

    @Override
    public void deleteProduct(long id) {
        Optional<Product> product = findProductById(id);
        product.ifPresent(manager::remove);
    }

    @Override
    public List<Product> findAllProducts() {
        return manager.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }

    @Override
    public Optional<Product> updateProduct(Product product) {
        if (product.getId() == 0) {
            throw new RuntimeException("Ошибка при обновлении товара, товар не существует");
        }

        Product updatedProduct = manager.merge(product);
        return Optional.of(updatedProduct);
    }

    @Override
    public List<Product> findProductsByCategory(Category category) {
        return manager
                .createQuery("SELECT p FROM Product p WHERE p.category = :category", Product.class)
                .setParameter("category", category)
                .getResultList();
    }

    @Override
    public List<Product> findProductByPrice(BigDecimal price) {
        return manager
                .createQuery("SELECT p FROM Product p WHERE p.price >= :price", Product.class)
                .setParameter("price", price)
                .getResultList();
    }

}
