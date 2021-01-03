package com.buisness.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "DepartmentBuilder", toBuilder = true)
@Getter
public class DepartmentDTO {
    Integer id;
    AddressDTO address;

    @JsonPOJOBuilder(withPrefix = "")
    public static class DepartmentBuilder {
    }
}
