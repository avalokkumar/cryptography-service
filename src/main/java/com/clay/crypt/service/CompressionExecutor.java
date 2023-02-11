package com.clay.crypt.service;

import com.clay.crypt.exception.AlgorithmNotSupported;
import com.clay.crypt.util.ActionType;
import com.clay.crypt.util.CompressionAlgorithm;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;
import org.bouncycastle.util.io.Streams;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;


@Service
public class CompressionExecutor {

    private static Map<ActionType, Map<CompressionAlgorithm, Function<byte[], byte[]>>> actionMapping;

    @PostConstruct
    public void init() {
        actionMapping = new EnumMap<>(ActionType.class);
        Map<CompressionAlgorithm, Function<byte[], byte[]>> compressionMapping = new HashMap<>();
        Map<CompressionAlgorithm, Function<byte[], byte[]>> decompressionMapping = new HashMap<>();

        compressionMapping.put(CompressionAlgorithm.ZLIB, zlibCompression);
        compressionMapping.put(CompressionAlgorithm.BZIP2, bzip2Compression);

        decompressionMapping.put(CompressionAlgorithm.ZLIB, zlibDeCompression);
        decompressionMapping.put(CompressionAlgorithm.BZIP2, bzip2DeCompression);

        actionMapping.put(ActionType.COMPRESS, compressionMapping);
        actionMapping.put(ActionType.DECOMPRESS, decompressionMapping);
    }

    public byte[] execute(ActionType actionType, CompressionAlgorithm algorithm, byte[] data) throws IOException {
        Function<byte[], byte[]> executorFunction = actionMapping.get(actionType).entrySet().stream()
                .filter(entry -> entry.getKey().equals(algorithm))
                .map(Map.Entry::getValue).findAny().orElseThrow(() -> new AlgorithmNotSupported("Algorithm " + algorithm.name() + " is not supported"));

        return executorFunction.apply(data);
    }

    private final Function<byte[], byte[]> zlibCompression = (data) -> {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        byte[] buffer = new byte[data.length];
        int compressedDataLength = deflater.deflate(buffer);
        byte[] compressedData = new byte[compressedDataLength];
        System.arraycopy(buffer, 0, compressedData, 0, compressedDataLength);
        deflater.end();
        return compressedData;
    };

    private final Function<byte[], byte[]> zlibDeCompression = (data) -> {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        byte[] buffer = new byte[data.length * 10];
        int decompressedDataLength = 0;
        try {
            decompressedDataLength = inflater.inflate(buffer);
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        } finally {
            inflater.end();
        }
        byte[] decompressedData = new byte[decompressedDataLength];
        System.arraycopy(buffer, 0, decompressedData, 0, decompressedDataLength);
        inflater.end();
        return decompressedData;
    };

    private final Function<byte[], byte[]> bzip2Compression = (data) -> {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (BZip2CompressorOutputStream bzos = new BZip2CompressorOutputStream(bos)) {
            Streams.pipeAll(new ByteArrayInputStream(data), bzos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bos.toByteArray();
    };

    private final Function<byte[], byte[]> bzip2DeCompression = (data) -> {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (BZip2CompressorInputStream bzis = new BZip2CompressorInputStream(new ByteArrayInputStream(data))) {
            Streams.pipeAll(bzis, bos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bos.toByteArray();
    };
}