package com.clay.crypt.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HashResponse {
    private String originalData;
    private String hashValue;
    private String saltValue;
}
