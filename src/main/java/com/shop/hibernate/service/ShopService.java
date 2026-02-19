package com.shop.hibernate.service;

import com.shop.hibernate.model.Product;
import com.shop.hibernate.repository.CategoryDao;
import com.shop.hibernate.repository.ProductDao;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ShopService {
    private CategoryDao categoryDao;
    private ProductDao productDao;

    public Product createProduct(Product product){
        return productDao.createProduct(product);
    }








}
