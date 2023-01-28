package com.clay.crypt.util;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.*;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.SecureRandom;
import java.util.EnumMap;
import java.util.Map;

@Component
public class SecureRandomNumberGenerator {

    private Map<DigestType, Digest> digestMap;

    @PostConstruct
    public void init() {
        digestMap = new EnumMap<>(DigestType.class);
        digestMap.put(DigestType.SHA1_DIGEST, new SHA1Digest());
        digestMap.put(DigestType.SHA256_DIGEST, new SHA256Digest());
        digestMap.put(DigestType.SHA3_DIGEST, new SHA3Digest());
        digestMap.put(DigestType.SHA512_DIGEST, new SHA512Digest());
        digestMap.put(DigestType.RIPEMD160_DIGEST, new RIPEMD160Digest());
        digestMap.put(DigestType.MD5_DIGEST, new MD5Digest());
        digestMap.put(DigestType.WHIRLPOOL_DIGEST, new WhirlpoolDigest());
    }

    public byte[] generateRandomNumber(DigestType digestType, int numBytes) {
        DigestRandomGenerator randomGenerator = new DigestRandomGenerator(digestMap.get(digestType));
        // seed the generator with current time
        randomGenerator.addSeedMaterial(System.currentTimeMillis());
        // seed the generator with additional entropy from SecureRandom
        randomGenerator.addSeedMaterial(SecureRandom.getSeed(8));
        byte[] randomBytes = new byte[numBytes];
        randomGenerator.nextBytes(randomBytes);
        return randomBytes;
    }
}
