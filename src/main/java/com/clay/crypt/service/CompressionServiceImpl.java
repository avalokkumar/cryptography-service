package com.clay.crypt.service;

import com.clay.crypt.entity.Compression;
import com.clay.crypt.exception.CompressionNotFoundException;
import com.clay.crypt.exception.DataNotFoundException;
import com.clay.crypt.mapper.CompressionMapper;
import com.clay.crypt.model.CompressionDTO;
import com.clay.crypt.repository.CompressionRepository;
import com.clay.crypt.util.ActionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class CompressionServiceImpl implements CompressionService {

    @Autowired
    private CompressionRepository compressionRepository;
    
    @Autowired
    private CompressionMapper compressionMapper;

    @Autowired
    private CompressionExecutor compressionExecutor;


    @Override
    public CompressionDTO compress(CompressionDTO compressionDTO) throws IOException {
        if (!StringUtils.hasText(compressionDTO.getData())) {
            throw new DataNotFoundException("Data is not present in the request");
        }
        byte[] compressedData = compressionExecutor.execute(ActionType.COMPRESS, compressionDTO.getAlgorithm(), compressionDTO.getData().getBytes(StandardCharsets.UTF_8));
        Compression compression = compressionMapper.toEntity(compressionDTO);
        compression.setCompressedData(new String(compressedData, StandardCharsets.UTF_8));
        compression.setTimestamp(Instant.now().toEpochMilli());
        compression = compressionRepository.save(compression);
        CompressionDTO updatedCompressionDTO = compressionMapper.toDto(compression);
        updatedCompressionDTO.setActionType(ActionType.COMPRESS);
        return updatedCompressionDTO;
    }

    @Override
    public CompressionDTO getById(String id) {
        Compression compression = compressionRepository.findById(id)
                .orElseThrow(() -> new CompressionNotFoundException("Compression detail with id '" + id + "' not found"));

        return compressionMapper.toDto(compression);
    }

    @Override
    public List<CompressionDTO> getAll() {
        List<Compression> compressions = compressionRepository.findAll();
        return compressionMapper.toDtoList(compressions);
    }

    @Override
    public CompressionDTO updateCompression(String id, CompressionDTO compressionDTO) {
        if (!compressionRepository.existsById(id)) {
            throw new CompressionNotFoundException("Compression detail with id '" + id + "' not found");
        }

        Compression compression = compressionMapper.toEntity(compressionDTO);
        compression.setId(id);
        compression = compressionRepository.save(compression);
        return compressionMapper.toDto(compression);
    }

    @Override
    public CompressionDTO decompress(CompressionDTO compressionDTO) throws IOException {
        if (!StringUtils.hasText(compressionDTO.getCompressedData())) {
            throw new DataNotFoundException("Data is not present in the request");
        }

        byte[] compressedData = Base64.getDecoder().decode(compressionDTO.getCompressedData());
        System.out.println("value " + new String(compressedData, StandardCharsets.UTF_8));
        byte[] decompressedData = compressionExecutor.execute(
                ActionType.DECOMPRESS,
                compressionDTO.getAlgorithm(),
                compressedData
        );
        Compression compression = compressionMapper.toEntity(compressionDTO);
        compression.setCompressedData(compressionDTO.getCompressedData());
        compression.setData(new String(decompressedData, StandardCharsets.UTF_8));
        compression.setTimestamp(Instant.now().toEpochMilli());

        compression = compressionRepository.save(compression);

        CompressionDTO updatedCompressedDTO = compressionMapper.toDto(compression);
        updatedCompressedDTO.setActionType(ActionType.COMPRESS);
        return updatedCompressedDTO;
    }


    @Override
    public void delete(String id) {
        if (!compressionRepository.existsById(id)) {
            throw new CompressionNotFoundException("Compression with id '" + id + "' not found");
        }
        compressionRepository.deleteById(id);
    }
}
