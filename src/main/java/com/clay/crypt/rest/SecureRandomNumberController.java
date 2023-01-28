package com.clay.crypt.rest;

import com.clay.crypt.model.SecureRandomNumberDTO;
import com.clay.crypt.service.SecureRandomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/secure-random-number")
public class SecureRandomNumberController {

    @Autowired
    private SecureRandomNumberService secureRandomNumberService;

    @PostMapping
    public ResponseEntity<SecureRandomNumberDTO> createSecureRandomNumber(@RequestBody SecureRandomNumberDTO secureRandomNumberDTO) {

        return new ResponseEntity<>(secureRandomNumberService.create(secureRandomNumberDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SecureRandomNumberDTO>> getAllSecureRandomNumber() {
        return new ResponseEntity<>(secureRandomNumberService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SecureRandomNumberDTO> getSecureRandomNumber(@PathVariable String id) {
        return new ResponseEntity<>(secureRandomNumberService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SecureRandomNumberDTO> updateSecureRandomNumber(@PathVariable String id, @RequestBody SecureRandomNumberDTO secureRandomNumberDTO) {
        secureRandomNumberDTO.setId(id);
        return new ResponseEntity<>(secureRandomNumberService.update(secureRandomNumberDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSecureRandomNumber(@PathVariable String id) {
        secureRandomNumberService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
