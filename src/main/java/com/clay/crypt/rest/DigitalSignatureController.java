package com.clay.crypt.rest;

import com.clay.crypt.model.DigitalSignatureDTO;
import com.clay.crypt.service.DigitalSignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.util.List;

/**
 * APIs to perform crud for digital signature
 */
@RestController
@RequestMapping("/api/v1/digital-signatures")
public class DigitalSignatureController {

    @Autowired
    private DigitalSignatureService digitalSignatureService;

    @PostMapping
    public ResponseEntity<DigitalSignatureDTO> createDigitalSignature(@RequestBody DigitalSignatureDTO digitalSignatureDto) throws NoSuchAlgorithmException, NoSuchProviderException, SignatureException, InvalidKeyException {

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

    //Update Not Supported
//    @PutMapping("/{id}")
    public DigitalSignatureDTO updateDigitalSignature(@PathVariable String id, @RequestBody DigitalSignatureDTO digitalSignatureDTO) {
        return digitalSignatureService.update(id, digitalSignatureDTO);
    }

}