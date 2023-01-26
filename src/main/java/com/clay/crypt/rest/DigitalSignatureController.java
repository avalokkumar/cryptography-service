package com.clay.crypt.rest;

import com.clay.crypt.model.DigitalSignatureDTO;
import com.clay.crypt.service.DigitalSignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/digital-signature")
public class DigitalSignatureController {

    @Autowired
    private DigitalSignatureService digitalSignatureService;

    @PostMapping
    public ResponseEntity<DigitalSignatureDTO> createDigitalSignature(@RequestBody DigitalSignatureDTO digitalSignatureDto) {

        DigitalSignatureDTO createdDigitalSignature = digitalSignatureService.create(digitalSignatureDto);
        return new ResponseEntity<>(createdDigitalSignature, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DigitalSignatureDTO> getDigitalSignature(@PathVariable("id") String id) {
        return new ResponseEntity<>( digitalSignatureService.get(id), HttpStatus.OK);
    }

    @GetMapping
    public List<DigitalSignatureDTO> getAllDigitalSignatures() {
        return digitalSignatureService.getAll();
    }

    @DeleteMapping("/{id}")
    public void deleteDigitalSignature(@PathVariable String id) {
        digitalSignatureService.delete(id);
    }

    @PutMapping("/{id}")
    public DigitalSignatureDTO updateDigitalSignature(@PathVariable String id, @RequestBody DigitalSignatureDTO digitalSignatureDTO) {
        return digitalSignatureService.update(id, digitalSignatureDTO);
    }

}