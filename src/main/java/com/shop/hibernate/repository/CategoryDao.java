package com.shop.hibernate.repository;

import com.shop.hibernate.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDao {

    Optional<Category> findCategoryById(long id);

    Category createCategory(Category category);

    void deleteCategory(Category category);

    List<Category> findAllCategories();

    Optional<Category> updateCategory(Category category);
}
