package com.clay.crypt.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.stereotype.Component;

import java.security.*;

@Component
public class DigitalSignatureGenerator {

    public static byte[] generate(String algorithm, PrivateKey privateKey, String data) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException {
        Security.addProvider(new BouncyCastleProvider());
        Signature signature = Signature.getInstance(algorithm, "BC");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        return signature.sign();
    }
}
