package com.clay.crypt.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "digital_signature")
public class DigitalSignature {

    private String id;
    private String algorithm;
    private String data;
    private String digitalSignature;
    private String privateKey;
    private String publicKey;

    private String generateSignature(String data, PrivateKey privateKey, String algorithm) throws Exception {
        Signature sign = Signature.getInstance(algorithm);
        sign.initSign(privateKey);
        sign.update(data.getBytes());
        return Base64.getEncoder().encodeToString(sign.sign());
    }
}
