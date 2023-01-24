package com.clay.crypt.repository;

import com.clay.crypt.entity.Hash;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashRepository extends MongoRepository<Hash, String> {

}
