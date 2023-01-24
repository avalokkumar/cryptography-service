package com.clay.crypt.util;

public enum HashingAlgorithm {
    SHA_1("SHA-1"),
    SHA_256("SHA-256"),
    SHA_512("SHA-512");

    private String algorithm;

    HashingAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }
}
