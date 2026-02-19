package com.shop.hibernate.service;

import com.shop.hibernate.dto.ProductWithoutCategoryDto;
import com.shop.hibernate.enums.Status;
import com.shop.hibernate.model.Category;
import com.shop.hibernate.model.Product;
import com.shop.hibernate.repository.CategoryDao;
import com.shop.hibernate.repository.ProductDao;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final CategoryDao categoryDao;
    private final ProductDao productDao;

    @Transactional
    public Product createProduct(Product product) {
        long categoryId = product.getCategory().getId();
        Category categoryInBase = findCategoryById(categoryId);
        product.setCategory(categoryInBase);
        return productDao.createProduct(product);
    }

    public Product getProduct(long id) {
        return productDao.findProductById(id)
                .orElseThrow(() -> new RuntimeException("Ошибка при поиске продукта"));
    }

    @Transactional
    public void removeProduct(long id) {
        Product product = getProduct(id);
        product.setStatus(Status.DELETED);
        product.setDeletedAt(LocalDateTime.now());
    }

    public List<Product> findAllProducts() {
        return productDao.findAllProducts();
    }

    @Transactional
    public Product updateProduct(Product product) {
        long categoryId = product.getCategory().getId();
        Category categoryInBase = findCategoryById(categoryId);
        product.setCategory(categoryInBase);

        return productDao.updateProduct(product)
                .orElseThrow(() -> new RuntimeException("Ошибка при обновлении продукта"));
    }

    public List<Product> findProductsByCategory(long categoryId) {
        Category foundCategory = findCategoryById(categoryId);
        return productDao.findProductsByCategory(foundCategory);
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
        Category category = findCategoryById(id);
        category.setStatus(Status.DELETED);
        category.setDeletedAt(LocalDateTime.now());
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
    public Category createCategoryWithProducts(Category category, List<ProductWithoutCategoryDto> products) {
        Category createdCategory = createCategory(category);

        for (ProductWithoutCategoryDto productDto : products) {
            Product product = new Product
                    (productDto.getName(), productDto.getPrice(), createdCategory);
            productDao.createProduct(product);
        }

        return createdCategory;
    }


}
