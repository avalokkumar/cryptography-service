package com.clay.crypt.service;

import com.clay.crypt.model.CompressionDTO;

import java.io.IOException;
import java.util.List;

public interface CompressionService {

    CompressionDTO compress(CompressionDTO compressionDTO) throws IOException;

    CompressionDTO getById(String id);

    List<CompressionDTO> getAll();

    CompressionDTO updateCompression(String id, CompressionDTO compressionDTO);

    CompressionDTO decompress(CompressionDTO compressionDTO) throws IOException;

    void delete(String id);
}
