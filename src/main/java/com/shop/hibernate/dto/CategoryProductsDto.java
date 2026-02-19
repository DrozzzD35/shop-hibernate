package com.shop.hibernate.dto;

import com.shop.hibernate.model.Category;
import com.shop.hibernate.model.Product;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
public class CategoryProductsDto {
    private Category category;
    private List<Product> products;
}
