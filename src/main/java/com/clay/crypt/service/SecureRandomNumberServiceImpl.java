package com.clay.crypt.service;

import com.clay.crypt.entity.SecureRandomNumber;
import com.clay.crypt.exception.SecureRandomNumberNotFoundException;
import com.clay.crypt.mapper.SecureRandomNumberMapper;
import com.clay.crypt.model.SecureRandomNumberDTO;
import com.clay.crypt.repository.SecureRandomNumberRepository;
import com.clay.crypt.util.SecureRandomNumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SecureRandomNumberServiceImpl implements SecureRandomNumberService {

    @Autowired
    private SecureRandomNumberRepository repository;

    @Autowired
    private SecureRandomNumberGenerator secureRandomNumberGenerator;

    @Autowired
    private SecureRandomNumberMapper mapper;

    @Override
    public SecureRandomNumberDTO create(SecureRandomNumberDTO dto) {
        SecureRandomNumber entity = mapper.dtoToEntity(dto);
        byte[] secureRandomNum = secureRandomNumberGenerator.generateRandomNumber(dto.getDigestType(), dto.getNumBytes());
        entity.setRandomNumber(secureRandomNum);
        entity.setGeneratedTimestamp(Instant.now().getEpochSecond());
        entity.setRandomNumberBigNum(new BigInteger(secureRandomNum));
        SecureRandomNumber savedEntity = repository.save(entity);
        return mapper.entityToDto(savedEntity);
    }

    @Override
    public List<SecureRandomNumberDTO> findAll() {
        List<SecureRandomNumber> entities = repository.findAll();
        return entities.stream().map(mapper::entityToDto).collect(Collectors.toList());
    }

    @Override
    public SecureRandomNumberDTO findById(String id) {
        SecureRandomNumber entity = repository.findById(id)
                .orElseThrow(() -> new SecureRandomNumberNotFoundException("Secure Random Number Doesnt exist for this id " + id));

        return mapper.entityToDto(entity);
    }

    @Override
    public SecureRandomNumberDTO update(SecureRandomNumberDTO dto) {
        if (!repository.existsById(dto.getId())) {
            throw new SecureRandomNumberNotFoundException("Secure Random Number Doesnt exist for this id " + dto.getId());
        }

        SecureRandomNumber entity = mapper.dtoToEntity(dto);
        SecureRandomNumber updatedEntity = repository.save(entity);
        return mapper.entityToDto(updatedEntity);
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new SecureRandomNumberNotFoundException("Secure Random Number Doesnt exist for this id " + id);
        }
        repository.deleteById(id);
    }
}
