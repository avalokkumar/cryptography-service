package com.clay.crypt.rest;

import com.clay.crypt.model.CompressionDTO;
import com.clay.crypt.service.CompressionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/compressions")
public class CompressionController {

    private final CompressionService compressionService;

    @Autowired
    public CompressionController(CompressionService compressionService) {
        this.compressionService = compressionService;
    }

    @PostMapping("/compress")
    public ResponseEntity<CompressionDTO> compressData(@RequestBody CompressionDTO compressionDTO) throws IOException {
        CompressionDTO createdCompression = compressionService.compress(compressionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompression);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompressionDTO> getCompressionById(@PathVariable("id") String id) {
        CompressionDTO compression = compressionService.getById(id);
        return ResponseEntity.ok(compression);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompressionDTO> updateCompression(@PathVariable("id") String id, @RequestBody CompressionDTO compressionDTO) {
        CompressionDTO updatedCompression = compressionService.updateCompression(id, compressionDTO);
        return ResponseEntity.ok(updatedCompression);
    }


    @PostMapping("/decompress")
    public ResponseEntity<CompressionDTO> decompressData(@RequestBody CompressionDTO compressionDTO) throws IOException {
        CompressionDTO createdCompression = compressionService.decompress(compressionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompression);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompression(@PathVariable("id") String id) {
        compressionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
