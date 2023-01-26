package com.clay.crypt.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.clay.crypt.entity.Hash;
import com.clay.crypt.exception.InvalidHashingAlgorithmException;
import com.clay.crypt.model.HashResponse;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.springframework.stereotype.Component;

@Component
public class HashGenerator {
    private static final int SALT_BYTE_SIZE = 16;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private Map<String, Digest> digestMapping = new HashMap<>();

    @PostConstruct
    public void init() {
        digestMapping.put(HashingAlgorithm.SHA_1.getAlgorithm(), new SHA1Digest());
        digestMapping.put(HashingAlgorithm.SHA_256.getAlgorithm(), new SHA256Digest());
        digestMapping.put(HashingAlgorithm.SHA_512.getAlgorithm(), new SHA512Digest());
    }

    public HashResponse generate(String text, String algorithm) {
        boolean isValidAlgorithm = Arrays.stream(HashingAlgorithm.values())
                .anyMatch(hashingAlgorithm -> hashingAlgorithm.getAlgorithm().equalsIgnoreCase(algorithm));
        if (!isValidAlgorithm) {
            throw new InvalidHashingAlgorithmException("Invalid Hashing Algorithm provided");
        }

        byte[] salt = generateSalt();
        byte[] textWithSalt = concatenate(text.getBytes(), salt);
        Digest digest = digestMapping.get(algorithm);
        byte[] bytes = new byte[digest.getDigestSize()];
        digest.update(textWithSalt, 0, textWithSalt.length);
        digest.doFinal(bytes, 0);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return HashResponse.builder()
                .hashValue(sb.toString())
                .originalData(text)
                .saltValue(Arrays.toString(salt))
                .build();
    }

    private byte[] generateSalt() {
        byte[] salt = new byte[SALT_BYTE_SIZE];
        SECURE_RANDOM.nextBytes(salt);
        return salt;
    }

    private byte[] concatenate(byte[] first, byte[] second) {
        byte[] result = new byte[first.length + second.length];
        System.arraycopy(first, 0, result, 0, first.length);
        System.arraycopy(second, 0, result, first.length, second.length);
        return result;
    }
}