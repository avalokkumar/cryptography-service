package com.clay.crypt.service;

import com.clay.crypt.entity.DigitalSignature;
import com.clay.crypt.exception.DigitalSignatureNotFoundException;
import com.clay.crypt.mapper.DigitalSignatureMapper;
import com.clay.crypt.model.DigitalSignatureDTO;
import com.clay.crypt.repository.DigitalSignatureRepository;
import com.clay.crypt.util.DigitalSignatureGenerator;
import com.clay.crypt.util.KeyGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.*;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class DigitalSignatureServiceImpl implements DigitalSignatureService {

    private static final String KEY_ALGORITHM = "ECDSA";

    @PostConstruct
    public void init() {
        Security.addProvider(new BouncyCastleProvider());
    }

    @Autowired
    private DigitalSignatureRepository digitalSignatureRepository;

    @Autowired
    private DigitalSignatureMapper digitalSignatureMapper;

    @Override
    public DigitalSignatureDTO create(DigitalSignatureDTO digitalSignatureDTO) throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {
        DigitalSignature digitalSignature = digitalSignatureMapper.toDigitalSignatureEntity(digitalSignatureDTO);
        KeyPair keyPair = KeyGenerator.generateKeyPair(KEY_ALGORITHM);
        digitalSignature.setPrivateKey(toBase64(keyPair.getPrivate().getEncoded()));
        digitalSignature.setPublicKey(toBase64(keyPair.getPublic().getEncoded()));
        //Generate digital Signature
        byte[] digitalSignatureBytes = DigitalSignatureGenerator.generate(
                digitalSignatureDTO.getAlgorithm().getAlgorithmName(),
                keyPair.getPrivate(), digitalSignature.getData()
        );

        digitalSignature.setDigitalSignature(toBase64(digitalSignatureBytes));
        DigitalSignature savedDigitalSignature = digitalSignatureRepository.save(digitalSignature);
        return digitalSignatureMapper.toDigitalSignatureDTO(savedDigitalSignature);
    }

    @Override
    public DigitalSignatureDTO update(String id, DigitalSignatureDTO digitalSignatureDTO) {

        if (!digitalSignatureRepository.existsById(id)) {
            throw new DigitalSignatureNotFoundException("Digital Signature not found with id: " + id);
        }

        DigitalSignature digitalSignature = digitalSignatureMapper.toDigitalSignatureEntity(digitalSignatureDTO);
        DigitalSignature updatedDigitalSignature = digitalSignatureRepository.save(digitalSignature);
        return digitalSignatureMapper.toDigitalSignatureDTO(updatedDigitalSignature);
    }

    @Override
    public DigitalSignatureDTO get(String id) {
        Optional<DigitalSignature> digitalSignature = digitalSignatureRepository.findById(id);
        if (digitalSignature.isPresent()) {
            return digitalSignatureMapper.toDigitalSignatureDTO(digitalSignature.get());
        } else {
            throw new DigitalSignatureNotFoundException("Digital Signature not found for id: " + id);
        }
    }

    @Override
    public void delete(String id) {
        DigitalSignature digitalSignature = digitalSignatureRepository.findById(id)
                .orElseThrow(() -> new DigitalSignatureNotFoundException("Digital Signature not found with id: " + id));
        digitalSignatureRepository.delete(digitalSignature);
    }

    @Override
    public List<DigitalSignatureDTO> getAll() {
        List<DigitalSignature> digitalSignatures = digitalSignatureRepository.findAll();
        return digitalSignatureMapper.toListDto(digitalSignatures);
    }


    public String toBase64(byte[] byteValue) {
        return Base64.getEncoder().encodeToString(byteValue);
    }
}
