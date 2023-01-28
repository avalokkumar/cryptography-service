package com.clay.crypt.model;

import com.clay.crypt.util.DigestType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigInteger;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SecureRandomNumberDTO {

    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "random_number")
    private byte[] randomNumber;

    @JsonProperty(value = "num_bytes")
    private int numBytes;

    @JsonProperty(value = "random_number_big_num")
    private BigInteger randomNumberBigNum;

    @JsonProperty(value = "generated_timestamp")
    private long generatedTimestamp;

    @JsonProperty(value = "digest_type")
    private DigestType digestType;
}