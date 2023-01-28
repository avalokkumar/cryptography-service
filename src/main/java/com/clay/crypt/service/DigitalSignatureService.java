package com.clay.crypt.service;

import com.clay.crypt.model.DigitalSignatureDTO;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.List;

public interface DigitalSignatureService {


    DigitalSignatureDTO create(DigitalSignatureDTO digitalSignatureDTO) throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException;

    DigitalSignatureDTO update(String id, DigitalSignatureDTO digitalSignatureDTO);

    DigitalSignatureDTO get(String id);

    void delete(String id);

    List<DigitalSignatureDTO> getAll();
}
