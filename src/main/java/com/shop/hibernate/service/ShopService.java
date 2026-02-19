package com.shop.hibernate.service;

import com.shop.hibernate.model.Category;
import com.shop.hibernate.model.Product;
import com.shop.hibernate.repository.CategoryDao;
import com.shop.hibernate.repository.ProductDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Transactional
    public Product createProduct(Product product) {
        return productDao.createProduct(product);
    }

    public Product getProduct(long id) {
        return productDao.findProductById(id)
                .orElseThrow(() -> new RuntimeException("Ошибка при поиске продукта"));
    }

    @Transactional
    public void removeProduct(long id) {
        productDao.deleteProduct(id);
    }

    public List<Product> findAllProducts() {
        return productDao.findAllProducts();
    }

    @Transactional
    public Product updateProduct(Product product) {
        return productDao.updateProduct(product)
                .orElseThrow(() -> new RuntimeException("Ошибка при обновлении продукта"));
    }

    public List<Product> findProductsByCategory(Category category) {
        return productDao.findProductsByCategory(category);
    }

    public List<Product> findProductByPrice(BigDecimal price) {
        return productDao.findProductByPrice(price);
    }

    public Category findCategoryById(long id) {
        return categoryDao.findCategoryById(id)
                .orElseThrow(() -> new RuntimeException("Ошибка при поиске категории"));
    }

    @Transactional
    public Category createCategory(Category category) {
        return categoryDao.createCategory(category);
    }

    @Transactional
    public void removeCategory(long id) {
        categoryDao.deleteCategory(id);
    }

    public List<Category> findAllCategories() {
        return categoryDao.findAllCategories();
    }

    @Transactional
    public Category updateCategory(Category category) {
        return categoryDao.updateCategory(category)
                .orElseThrow(() -> new RuntimeException("Ошибка при обновлении категории"));
    }

    @Transactional
    public Category createCategoryWithProducts(Category category, List<Product> products) {
        Category createdCategory = createCategory(category);

        for (Product product: products){
            product.setCategory(createdCategory);
            productDao.createProduct(product);
        }

        return createdCategory;
    }


}
