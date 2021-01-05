package com.buisness.management.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder(builderClassName = "AddressBuilder", toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AddressDTO {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("street")
    private String street;
    @JsonProperty("city")
    private String city;
    @JsonProperty("number")
    private Integer number;
    @JsonProperty("postalCode")
    private String postalCode;

    @JsonPOJOBuilder(withPrefix = "")
    public static class AddressBuilder {
    }
}
