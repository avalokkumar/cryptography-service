package com.clay.crypt.util;

public enum SignatureAlgorithm {

    SHA256_WITH_RSA("SHA256withRSA"),
    SHA512_WITH_ECDSA("SHA512withECDSA"),
    NONE_WITH_RSA("NONEwithRSA");

    private String algorithmName;

    SignatureAlgorithm(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }
}
