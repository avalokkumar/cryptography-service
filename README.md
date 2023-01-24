## Cryptography-Service

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

### Usage

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




