package com.buisness.management.model;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Client {
    Integer id;
    String firstName;
    String lastName;
    Address address;
}
