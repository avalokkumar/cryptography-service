package com.clay.crypt.mapper;


import com.clay.crypt.entity.Hash;
import com.clay.crypt.model.CreateHashDTO;
import com.clay.crypt.model.HashDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface HashMapper {

//    @Mapping(target = "id", ignore = true)
    HashDTO toDto(Hash hash);

    @Mapping(target = "id", ignore = true)
    Hash toEntity(HashDTO hashDTO);

    Hash createHashDTOtoEntity(CreateHashDTO hashDTO);

    default List<HashDTO> toListDto(Collection<Hash> collection) {
        return collection.stream().map(this::toDto).collect(Collectors.toList());
    }
}
