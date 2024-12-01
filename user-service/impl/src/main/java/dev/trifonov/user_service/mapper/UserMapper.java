package dev.trifonov.user_service.mapper;

import dev.trifonov.user_service.dto.UserDetailsDto;
import dev.trifonov.user_service.dto.UserPreviewDto;
import dev.trifonov.user_service.entity.MarketplaceUser;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements AbstractMapper<MarketplaceUser, UserDetailsDto, UserPreviewDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserPreviewDto convertToPreviewDto(MarketplaceUser entityInstance) {
        return modelMapper.map(entityInstance, UserPreviewDto.class);
    }

    @Override
    public UserDetailsDto convertToDetailsDto(MarketplaceUser entityInstance) {
        return modelMapper.map(entityInstance, UserDetailsDto.class);
    }

    @Override
    public MarketplaceUser convertToEntity(UserDetailsDto dtoInstance) {
        return modelMapper.map(dtoInstance, MarketplaceUser.class);
    }
}
