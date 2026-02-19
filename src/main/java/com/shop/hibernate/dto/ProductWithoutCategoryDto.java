package com.shop.hibernate.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductWithoutCategoryDto {
    private String name;
    private BigDecimal price;
}
