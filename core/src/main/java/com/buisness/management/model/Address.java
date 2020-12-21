package com.buisness.management.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Address {
    String street;
    String city;
    Integer number;
    String postalCode;
}
