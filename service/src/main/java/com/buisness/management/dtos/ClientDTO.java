package com.buisness.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "ClientBuilder", toBuilder = true)
@Getter
public class ClientDTO {
    Integer id;
    String firstName;
    String lastName;
    AddressDTO address;

    @JsonPOJOBuilder(withPrefix = "")
    public static class ClientBuilder {
    }
}
