package com.buisness.management.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Department {
    Integer id;
    Address address;
}
