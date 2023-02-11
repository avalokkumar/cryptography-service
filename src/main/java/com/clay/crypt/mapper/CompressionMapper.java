package com.clay.crypt.mapper;

import com.clay.crypt.entity.Compression;
import com.clay.crypt.model.CompressionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface CompressionMapper {

    @Mapping(target = "id", ignore = true)
    Compression toEntity(CompressionDTO compressionDTO);

    CompressionDTO toDto(Compression compression);

    List<CompressionDTO> toDtoList(List<Compression> compressionList);
}
