package com.clay.crypt.service;

import com.clay.crypt.exception.HashNotFoundException;
import com.clay.crypt.exception.DataNotFoundException;
import com.clay.crypt.model.CreateHashDTO;
import com.clay.crypt.model.HashDTO;

import java.util.List;

public interface HashService {

    HashDTO createHash(CreateHashDTO hashDTO) throws DataNotFoundException;

    HashDTO getHash(String id) throws HashNotFoundException;

    void deleteHash(String id) throws HashNotFoundException;

    HashDTO updateHash(String id, HashDTO hash) throws HashNotFoundException, DataNotFoundException;

    List<HashDTO> getAllHashes();
}
