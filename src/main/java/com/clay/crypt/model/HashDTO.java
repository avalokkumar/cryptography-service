package com.clay.crypt.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HashDTO {

    private String id;
    @JsonProperty(value = "hash_value")
    private String hashValue;
    private String algorithm;
    private String data;
    private long timestamp;
    private String userId;
    @JsonProperty(value = "username")
    private String userName;
    private String salt;
    private List<String> additionalProperties;

}