package com.shop.hibernate.repository;

import com.shop.hibernate.model.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepository implements CategoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Category> findCategoryById(long id) {
        Category category = entityManager.find(Category.class, id);
        return Optional.ofNullable(category);
    }

    @Override
    public Category createCategory(Category category) {
        try {
            entityManager.persist(category);
            return category;
        } catch (Exception e){
            throw new RuntimeException("Ошибка при создании категории");
        }


//        if (category.getId() == 0) {
//            entityManager.persist(category);
//            return category;
//        } else {
//            return null;
//        }
    }

    @Override
    public void deleteCategory(Category category) {
        Optional<Category> deleteCategory = findCategoryById(category.getId());
        entityManager.remove(deleteCategory);
    }

    @Override
    public List<Category> findAllCategories() {
        return entityManager.createQuery("SELECT c FROM Category c", Category.class)
                .getResultList();
    }

    @Override
    public Optional<Category> updateCategory(Category category) {
        if (category.getId() != 0) {
            Category updated = entityManager.merge(category);
            return Optional.of(updated);
        }
        return Optional.empty();
    }
}
