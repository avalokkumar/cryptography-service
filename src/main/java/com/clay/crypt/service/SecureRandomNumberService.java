package com.clay.crypt.service;

import com.clay.crypt.model.SecureRandomNumberDTO;

import java.util.List;

public interface SecureRandomNumberService {
    SecureRandomNumberDTO create(SecureRandomNumberDTO dto);

    List<SecureRandomNumberDTO> findAll();

    SecureRandomNumberDTO findById(String id);

    SecureRandomNumberDTO update(SecureRandomNumberDTO dto);

    void delete(String id);
}
