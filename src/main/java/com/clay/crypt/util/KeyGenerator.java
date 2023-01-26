package com.clay.crypt.util;

import java.security.*;

public class KeyGenerator {
    private static final String PROVIDER = "BC";

    public static KeyPair generateKeyPair(String algorithm) throws NoSuchAlgorithmException, NoSuchProviderException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance(algorithm, PROVIDER);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        keyGen.initialize(256, random);
        return keyGen.generateKeyPair();
    }
}
