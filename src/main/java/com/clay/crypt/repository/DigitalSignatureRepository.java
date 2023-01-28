package com.clay.crypt.repository;

import com.clay.crypt.entity.DigitalSignature;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DigitalSignatureRepository extends MongoRepository<DigitalSignature, String> {

}
