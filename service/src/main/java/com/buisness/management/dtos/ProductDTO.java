package com.buisness.management.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "ProductBuilder", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
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
