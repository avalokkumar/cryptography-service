## Cryptography-Service

> 

This service provides APIs for various cryptography operations such as AES, DES, RSA encryption, and SHA-1, SHA-256 message digest generation. It uses Bouncy Castle library to perform these operations and MongoDB to store the generated hash values. The service is built using Spring Boot and Gradle and can be run using Docker.

### Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

Prerequisites

* Java 11 or above
* MongoDB 4.2 or above
* Gradle 6.7 or above
* Docker 19.03 or above

### Installation

* Clone the repository

```
git clone https://github.com/avalokkumar/cryptography-service.git
```

* Build the project using gradle

```
./gradlew clean build
```

* Build the Docker image

```
docker build -t cryptography-service .
```

* Run the MongoDB container

```
docker run -d --name mongodb -p 27017:27017 mongo
```

* Run the service container

```
docker run -d --name cryptography-service --link mongodb:mongodb -p 8080:8080 cryptography-service
```

### Hashing APIs

The service provides the following APIs:


* Create Hash
* Update Hash
* Delete Hash
* Get Hash
* Get All Hash


#### Generates a hash value for the given text and algorithm.

Request:

```
POST /hash/generate

{
    "data": "Good morning",
    "algorithm": "SHA-512"
}
```

Response:

```
HTTP/1.1 200 OK

{
    "algorithm": "SHA-512",
    "data": "Good morning",
    "timestamp": 1674583356942,
    "salt": "[110, -74, -12, -3, 73, 111, 96, 89, -102, 110, 16, -91, 88, 70, 110, -120]",
    "hash_value": "76971c8084b3d53f1b28b208b37c46bfd7e3ed4dd199482f1ed8b5e06b199414c962ab0464b001bd4cf211a0d33b885e504cbabe6d96ca3ca4472ae2823713ef"
}
```

---

### Digital Signature Service

The service provides the following APIs:


* Create DigitalSignature

#### Request
`/api/v1/digital-signatures`

```
{
    "algorithm": "SHA512_WITH_ECDSA",
    "data": "Hello Clay"
}
```
#### Response

```
{
    "_id": "63d55d15062ba420f10174d6",
    "algorithm": "SHA512_WITH_ECDSA",
    "data": "Hello Clay",
    "digital_signature": "MEYCIQCf0NMKLGOWcsiUm1+6BhRzKnq4hz2gR3I/L7hzWbQpNwIhANj3busnOSr9udFEnKDBREcAEZQotQQaj0xOcHyKsT2n"
}
```


* Delete DigitalSignature

DELETE `/api/v1/digital-signatures/63d55c7efa99144fdcbf2751`


* Get DigitalSignature

GET `/api/v1/digital-signatures/63d55c7efa99144fdcbf2751`


* Get All DigitalSignatures

GET `/api/v1/digital-signatures`

---

### Secure Random Numbers Service

#### Usage

1. As a cryptographic key: You can use the randomNumber as a key for symmetric encryption algorithms such as AES, or as a private key for asymmetric encryption algorithms such as RSA.

2. As a nonce: You can use the randomNumber as a nonce (number used once) in cryptographic protocols such as authenticated encryption or digital signatures.

3. As a seed: You can use the randomNumber as a seed to initialize a random number generator such as java.util.Random.

4. As a unique identifier: You can use the randomNumber as a unique identifier for something like database records or files.

5. As a random password: You can use the randomNumber to generate random password for the user.


> example of using the randomNumber as an AES key:

```
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

SecureRandomNumber srn = new SecureRandomNumber(16);
byte[] randomNumber = srn.getRandomNumber();
SecretKeySpec key = new SecretKeySpec(randomNumber, "AES");
Cipher cipher = Cipher.getInstance("AES");
cipher.init(Cipher.ENCRYPT_MODE, key);

```

#### The service provides the following APIs:


* Create Secure Random Number

#### Request
POST `/api/v1/secure-random-number`

```
{
    "num_bytes": 64,
    "digest_type": "SHA256_DIGEST"
}
```

#### Response

```
{
    "id": "63d57864f3a1904718b90f11",
    "random_number": "gUtscysDL8HjhGlAOYEECLax8zKVgk5KT8uM4IyWpOE0f9PeCdw0/L9poUw6CMui7qzCUogTxOMKz7aROMQNHA==",
    "num_bytes": 64,
    "random_number_big_num": -6636099027140936701590624590939797398478701258910850814085605311060271952373198945652576518500935391795369395322370233812842057037828275547490611566998244,
    "generated_timestamp": 1674934372,
    "digest_type": "SHA256_DIGEST"
}
```

* Get Secure Random Number by Id

GET `/api/v1/secure-random-number/63d5776bf3a1904718b90f0f`


```
{
        "id": "63d5776bf3a1904718b90f0f",
        "random_number": "XK3NkHuXC99rmQsceJd3XA==",
        "num_bytes": 16,
        "random_number_big_num": 123191412309812196207583280979850524508,
        "generated_timestamp": 1674934123,
        "digest_type": "SHA256_DIGEST"
    }
```

* Get All Secure Random Number

GET `/api/v1/secure-random-number`

```
[
    {
        "id": "63d5776bf3a1904718b90f0f",
        "random_number": "XK3NkHuXC99rmQsceJd3XA==",
        "num_bytes": 16,
        "random_number_big_num": 123191412309812196207583280979850524508,
        "generated_timestamp": 1674934123,
        "digest_type": "SHA256_DIGEST"
    },
    {
        "id": "63d57864f3a1904718b90f11",
        "random_number": "gUtscysDL8HjhGlAOYEECLax8zKVgk5KT8uM4IyWpOE0f9PeCdw0/L9poUw6CMui7qzCUogTxOMKz7aROMQNHA==",
        "num_bytes": 64,
        "random_number_big_num": -6636099027140936701590624590939797398478701258910850814085605311060271952373198945652576518500935391795369395322370233812842057037828275547490611566998244,
        "generated_timestamp": 1674934372,
        "digest_type": "SHA256_DIGEST"
    }
]
```

* Delete Secure Random Number

DELETE `/api/v1/secure-random-number/63d57771f3a1904718b90f10`
