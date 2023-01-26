package com.clay.crypt.model;

import lombok.Data;

import java.util.Date;

@Data
public class DigitalSignatureDTO {

    private String algorithm;
    private String data;
    private String signature;
    private Date timestamp;
}
