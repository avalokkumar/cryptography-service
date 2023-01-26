package com.clay.crypt.rest;

import com.clay.crypt.exception.HashNotFoundException;
import com.clay.crypt.exception.DataNotFoundException;
import com.clay.crypt.model.CreateHashDTO;
import com.clay.crypt.model.HashDTO;
import com.clay.crypt.service.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hash")
public class HashController {

    @Autowired
    private HashService hashService;

    @GetMapping
    public List<HashDTO> getAllHashes() throws DataNotFoundException {
        return hashService.getAllHashes();
    }

    @PostMapping
    public HashDTO createHash(@RequestBody CreateHashDTO hashDTO) throws DataNotFoundException {
        return hashService.createHash(hashDTO);
    }

    @GetMapping("/{id}")
    public HashDTO getHash(@PathVariable String id) throws HashNotFoundException {
        return hashService.getHash(id);
    }

    @PutMapping("/{id}")
    public HashDTO updateHash(@PathVariable String id, @RequestBody HashDTO hashDTO) throws DataNotFoundException, HashNotFoundException {
        return hashService.updateHash(id,hashDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteHash(@PathVariable String id) throws HashNotFoundException {
        hashService.deleteHash(id);
    }
}