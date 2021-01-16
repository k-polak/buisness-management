package com.buisness.management.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "OrderBuilder", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderDTO {

    Integer id;
    ClientDTO client;
    List<OrderProductDTO> products;
    Float sum;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDate date;

    @JsonPOJOBuilder(withPrefix = "")
    public static class OrderBuilder {
    }
}
