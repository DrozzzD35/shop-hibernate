package com.shop.hibernate.dto;

import com.shop.hibernate.model.Category;
import lombok.Data;

import java.util.List;

@Data
public class CategoryWithProductsDto {
    private Category category;
    private List<ProductWithoutCategoryDto> products;
}
