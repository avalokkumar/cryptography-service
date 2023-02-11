package com.clay.crypt.model;

import com.clay.crypt.util.ActionType;
import com.clay.crypt.util.CompressionAlgorithm;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CompressionDTO {
    private ActionType actionType;
    private CompressionAlgorithm algorithm;
    private String data;
    private String compressedData;
    private long timestamp;
}
