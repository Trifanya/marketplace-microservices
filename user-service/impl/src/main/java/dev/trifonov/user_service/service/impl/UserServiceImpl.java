package dev.trifonov.user_service.service.impl;

import dev.trifonov.user_service.dto.UserDetailsDto;
import dev.trifonov.user_service.dto.UserPreviewDto;
import dev.trifonov.user_service.entity.User;
import dev.trifonov.user_service.mapper.AbstractMapper;
import dev.trifonov.user_service.repository.UserRepository;
import dev.trifonov.user_service.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AbstractMapper<User, UserDetailsDto, UserPreviewDto> mapper;

    @Override
    public UserDetailsDto getUserById(long userId) {
        return userRepository.findById(userId)
                .map(mapper::convertToDetailsDto)
                .orElseThrow(() -> new RuntimeException("Пользователь с указанынным id не найден."));
    }

    @Override
    public UserDetailsDto createUser(UserDetailsDto dto) {
        User userToSave = mapper.convertToEntity(dto);
        User savedUser = userRepository.save(userToSave);
        return mapper.convertToDetailsDto(savedUser);
    }

    @Override
    public UserDetailsDto updateUser(long userId, UserDetailsDto dto) {
        User userToSave = mapper.convertToEntity(dto);
        userToSave.setId(userId);
        User updatedUser = userRepository.save(userToSave);
        return mapper.convertToDetailsDto(updatedUser);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
