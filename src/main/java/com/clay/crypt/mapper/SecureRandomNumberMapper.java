package com.clay.crypt.mapper;

import com.clay.crypt.entity.SecureRandomNumber;
import com.clay.crypt.model.SecureRandomNumberDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SecureRandomNumberMapper {


    @Mapping(source = "digestType", target = "digestType")
    SecureRandomNumberDTO entityToDto(SecureRandomNumber entity);

    @Mapping(source = "digestType", target = "digestType")
    SecureRandomNumber dtoToEntity(SecureRandomNumberDTO dto);
}
