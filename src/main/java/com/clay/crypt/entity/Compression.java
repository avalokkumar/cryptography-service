package com.clay.crypt.entity;

import com.clay.crypt.util.CompressionAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "compression")
public class Compression {
  
  @Id
  private String id;
  private String data;
  private String compressionAlgorithm;
  private String compressedData;
  private long timestamp;

}
