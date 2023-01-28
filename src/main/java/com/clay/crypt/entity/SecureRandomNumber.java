package com.clay.crypt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "secure_random_number")
public class SecureRandomNumber {

    @Id
    private String id;

    private byte[] randomNumber;

    private BigInteger randomNumberBigNum;

    private int numBytes;

    private long generatedTimestamp;

    private String digestType;
}