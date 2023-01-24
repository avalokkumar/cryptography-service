package com.clay.crypt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CreateHashDTO {

    private String algorithm;
    private String data;
    @JsonProperty(value = "username")
    private String userName;
    private List<String> additionalProperties;
}
