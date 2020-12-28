package com.buisness.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "CreateEmployeeBuilder", toBuilder = true)
@Getter
public class CreateEmployeeDTO {
    String firstName;
    String lastName;
    Integer addressId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class CreateEmployeeBuilder {
    }
}
