package com.buisness.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "OrderProductBuilder", toBuilder = true)
@Getter
public class OrderProductDTO {
    ProductDTO product;
    Integer amount;

    @JsonPOJOBuilder(withPrefix = "")
    public static class OrderProductBuilder {
    }
}
