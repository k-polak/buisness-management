package com.buisness.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "EmployeeBuilder", toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    Integer id;
    String firstName;
    String lastName;
    AddressDTO address;

    @JsonPOJOBuilder(withPrefix = "")
    public static class EmployeeBuilder {
    }
}
