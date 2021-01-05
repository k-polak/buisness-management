package com.buisness.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "ClientBuilder", toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ClientDTO {
    Integer id;
    String firstName;
    String lastName;
    Integer addressId;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ClientBuilder {
    }
}
