package com.buisness.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "OrderBuilder", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreateOrderDTO {
    Integer clientId;
    Map<Integer, Integer> productAmountMap;   // key: product ID, value: amount

    @JsonPOJOBuilder(withPrefix = "")
    public static class OrderBuilder {
    }
}
