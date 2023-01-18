package com.security.cryptography.core.mapper.specification;

import com.security.cryptography.domain.dto.SymmetricKeyBlockCipherDataDto;
import com.security.cryptography.domain.entity.SymmetricKeyBlockCipherData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SymmetricKeyBlockCipherDataMapper {
    SymmetricKeyBlockCipherDataDto map(SymmetricKeyBlockCipherData symmetricKeyBlockCipherData);
    SymmetricKeyBlockCipherData map(SymmetricKeyBlockCipherDataDto symmetricKeyBlockCipherDataDto);
}
