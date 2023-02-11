package com.clay.crypt.repository;

import com.clay.crypt.entity.Compression;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompressionRepository extends MongoRepository<Compression, String> {

}
