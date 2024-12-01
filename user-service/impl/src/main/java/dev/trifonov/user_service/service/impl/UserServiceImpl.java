package dev.trifonov.user_service.service.impl;

import dev.trifonov.user_service.dto.UserDetailsDto;
import dev.trifonov.user_service.dto.UserPreviewDto;
import dev.trifonov.user_service.entity.MarketplaceUser;
import dev.trifonov.user_service.mapper.AbstractMapper;
import dev.trifonov.user_service.repository.UserRepository;
import dev.trifonov.user_service.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AbstractMapper<MarketplaceUser, UserDetailsDto, UserPreviewDto> mapper;

    @Override
    public UserDetailsDto getUserById(long userId) {
        return userRepository.findById(userId)
                .map(mapper::convertToDetailsDto)
                .orElseThrow(() -> new RuntimeException("Пользователь с указанынным id не найден."));
    }

    @Override
    public UserDetailsDto createUser(UserDetailsDto dto) {
        MarketplaceUser marketplaceUserToSave = mapper.convertToEntity(dto);
        MarketplaceUser savedMarketplaceUser = userRepository.save(marketplaceUserToSave);
        return mapper.convertToDetailsDto(savedMarketplaceUser);
    }

    @Override
    public UserDetailsDto updateUser(long userId, UserDetailsDto dto) {
        MarketplaceUser marketplaceUserToSave = mapper.convertToEntity(dto);
        marketplaceUserToSave.setId(userId);
        MarketplaceUser updatedMarketplaceUser = userRepository.save(marketplaceUserToSave);
        return mapper.convertToDetailsDto(updatedMarketplaceUser);
    }

    @Override
    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }
}
