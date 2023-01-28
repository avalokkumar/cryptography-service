package com.clay.crypt.util;

import org.bouncycastle.crypto.Digest;

public enum DigestType {

    SHA1_DIGEST("SHA1Digest", org.bouncycastle.crypto.digests.SHA1Digest.class),
    SHA256_DIGEST("SHA256Digest", org.bouncycastle.crypto.digests.SHA256Digest.class),
    SHA3_DIGEST("SHA3Digest", org.bouncycastle.crypto.digests.SHA3Digest.class),
    SHA512_DIGEST("SHA512Digest", org.bouncycastle.crypto.digests.SHA512Digest.class),
    RIPEMD160_DIGEST("RIPEMD160Digest", org.bouncycastle.crypto.digests.RIPEMD160Digest.class),
    MD5_DIGEST("MD5Digest", org.bouncycastle.crypto.digests.MD5Digest.class),
    WHIRLPOOL_DIGEST("WhirlpoolDigest", org.bouncycastle.crypto.digests.WhirlpoolDigest.class);


    private String digestType;

    private Class<? extends Digest> digestClass;

    DigestType(String digestType, Class<? extends Digest> digestClass) {
        this.digestType = digestType;
        this.digestClass = digestClass;
    }

    public String getDigestType() {
        return digestType;
    }

    public Class<? extends Digest> getDigestClass() {
        return digestClass;
    }
}
