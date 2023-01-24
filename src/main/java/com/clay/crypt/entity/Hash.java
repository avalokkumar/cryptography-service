package com.clay.crypt.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "hashes")
public class Hash {

    @Id
    private String id;
    private String hashValue;
    private String algorithm;
    private String data;
    private long timestamp;
    private String userName;
    private String salt;
    private List<String> additionalProperties;
}
