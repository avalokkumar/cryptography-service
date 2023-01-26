package com.clay.crypt.mapper;

import com.clay.crypt.entity.DigitalSignature;
import com.clay.crypt.model.DigitalSignatureDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface DigitalSignatureMapper {

    DigitalSignatureDTO toDigitalSignatureDTO(DigitalSignature digitalSignature);

    @Mapping(target = "id", ignore = true)
    DigitalSignature toDigitalSignatureEntity(DigitalSignatureDTO digitalSignatureDTO);

    default List<DigitalSignatureDTO> toListDto(Collection<DigitalSignature> collection) {
        return collection.stream().map(this::toDigitalSignatureDTO).collect(Collectors.toList());
    }
}
