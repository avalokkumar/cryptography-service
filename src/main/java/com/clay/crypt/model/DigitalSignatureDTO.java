package com.clay.crypt.model;

import com.clay.crypt.util.SignatureAlgorithm;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class DigitalSignatureDTO {

    @JsonProperty(value = "_id")
    private String id;

    @JsonProperty(value = "algorithm")
    private SignatureAlgorithm algorithm;

    @JsonProperty(value = "data")
    private String data;

    @JsonProperty(value = "digital_signature")
    private String digitalSignature;

    @JsonProperty(value = "timestamp")
    private Date timestamp;
}
