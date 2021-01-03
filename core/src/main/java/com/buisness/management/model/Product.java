package com.buisness.management.model;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Product {
    Integer id;
    String name;
    String code;
    Integer quantity;
}
