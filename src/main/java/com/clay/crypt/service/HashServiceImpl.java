package com.clay.crypt.service;

import com.clay.crypt.entity.Hash;
import com.clay.crypt.exception.HashNotFoundException;
import com.clay.crypt.exception.DataNotFoundException;
import com.clay.crypt.mapper.HashMapper;
import com.clay.crypt.model.CreateHashDTO;
import com.clay.crypt.model.HashDTO;
import com.clay.crypt.model.HashResponse;
import com.clay.crypt.repository.HashRepository;
import com.clay.crypt.util.HashGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class HashServiceImpl implements HashService {

    @Autowired
    private HashRepository hashRepository;

    @Autowired
    private HashMapper hashMapper;

    @Autowired
    private HashGenerator hashGenerator;

    @Override
    public HashDTO createHash(CreateHashDTO hashDTO) throws DataNotFoundException {

        if (hashDTO.getAlgorithm() == null) {
            throw new DataNotFoundException("Algorithm value is required");
        }

        if (hashDTO.getData() == null) {
            throw new DataNotFoundException("Data is required");
        }

        Hash hash = hashMapper.createHashDTOtoEntity(hashDTO);
        HashResponse hashResponse = hashGenerator.generate(hash.getData(), hash.getAlgorithm());
        hash.setHashValue(hashResponse.getHashValue());
        hash.setTimestamp(Instant.now().toEpochMilli());
        hash.setSalt(hashResponse.getSaltValue());

        return hashMapper.toDto(hashRepository.save(hash));
    }


    public HashDTO getHash(String id) throws HashNotFoundException {
        Hash hash = hashRepository.findById(id)
                .orElseThrow(() -> new HashNotFoundException("Hash not found with id: " + id));
        return hashMapper.toDto(hash);
    }

    @Transactional
    public HashDTO updateHash(String id, HashDTO hash) throws HashNotFoundException, DataNotFoundException {
        Hash existingHash = hashRepository.findById(id)
                .orElseThrow(() -> new HashNotFoundException("Hash not found with id: " + id));
        //validation
        if (hash.getHashValue() == null) {
            throw new DataNotFoundException("Hash value is required");
        }
        if (hash.getAlgorithm() == null) {
            throw new DataNotFoundException("Algorithm value is required");
        }
        existingHash.setHashValue(hash.getHashValue());
        existingHash.setAlgorithm(hash.getAlgorithm());
        existingHash.setSalt(hash.getSalt());
        existingHash.setAdditionalProperties(hash.getAdditionalProperties());

        return hashMapper.toDto(hashRepository.save(existingHash));
    }

    @Transactional
    public void deleteHash(String id) throws HashNotFoundException {
        Hash existingHash = hashRepository.findById(id)
                .orElseThrow(() -> new HashNotFoundException("Hash not found with id: " + id));
        hashRepository.delete(existingHash);
    }

    @Override
    public List<HashDTO> getAllHashes() {
        return hashMapper.toListDto(hashRepository.findAll());
    }
}