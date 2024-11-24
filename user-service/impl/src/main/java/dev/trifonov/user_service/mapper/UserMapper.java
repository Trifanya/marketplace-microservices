package dev.trifonov.user_service.mapper;

import dev.trifonov.user_service.dto.UserDetailsDto;
import dev.trifonov.user_service.dto.UserPreviewDto;
import dev.trifonov.user_service.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper implements AbstractMapper<User, UserDetailsDto, UserPreviewDto> {

    private final ModelMapper modelMapper;

    @Override
    public UserPreviewDto convertToPreviewDto(User entityInstance) {
        return modelMapper.map(entityInstance, UserPreviewDto.class);
    }

    @Override
    public UserDetailsDto convertToDetailsDto(User entityInstance) {
        return modelMapper.map(entityInstance, UserDetailsDto.class);
    }

    @Override
    public User convertToEntity(UserDetailsDto dtoInstance) {
        return modelMapper.map(dtoInstance, User.class);
    }
}
