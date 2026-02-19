package com.shop.hibernate.repository;

import com.shop.hibernate.model.Category;
import com.shop.hibernate.model.Product;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    Optional<Category> findCategoryById(long id);

    Category createCategory(Category category);

    void deleteCategory(long id);

    List<Category> findAllCategories();

    Optional<Category> updateCategory(Category category);

}
