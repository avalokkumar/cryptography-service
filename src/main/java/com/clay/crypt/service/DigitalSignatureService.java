package com.clay.crypt.service;

import com.clay.crypt.model.DigitalSignatureDTO;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;

public interface DigitalSignatureService {


    DigitalSignatureDTO create(DigitalSignatureDTO digitalSignatureDTO) throws NoSuchAlgorithmException, NoSuchProviderException;

    DigitalSignatureDTO update(String id, DigitalSignatureDTO digitalSignatureDTO);

    DigitalSignatureDTO get(String id);

    void delete(String id);

    List<DigitalSignatureDTO> getAll();
}
