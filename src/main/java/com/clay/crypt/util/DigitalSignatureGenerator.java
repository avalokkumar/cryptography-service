package com.clay.crypt.util;

import org.springframework.stereotype.Component;

import java.security.*;

public class DigitalSignatureGenerator {

    public static byte[] generate(String algorithm, PrivateKey privateKey, String data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException {
        Signature signature = Signature.getInstance(algorithm, "BC");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        return signature.sign();
    }
}
