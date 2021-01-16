package com.buisness.management.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Map;

@Builder
@Getter
public class Order {
    Integer id;
    Client client;
    Map<Product, Integer> products;
    Float sum;
    LocalDate date;
}
