package com.buisness.management.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "ProductBuilder", toBuilder = true)
@Getter
public class ProductDTO {
    Integer id;
    String name;
    String code;
    Integer quantity;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ProductBuilder {
    }
}
