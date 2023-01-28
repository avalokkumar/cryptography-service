package com.clay.crypt.repository;

import com.clay.crypt.entity.SecureRandomNumber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecureRandomNumberRepository extends MongoRepository<SecureRandomNumber, String> {

    @Query("{'digestType': ?0}")
    List<SecureRandomNumber> findByDigestType(String digestType);
}
