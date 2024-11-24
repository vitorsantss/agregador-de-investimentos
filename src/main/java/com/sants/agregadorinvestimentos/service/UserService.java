package com.sants.agregadorinvestimentos.service;

import com.sants.agregadorinvestimentos.controller.CreateUserDto;
import com.sants.agregadorinvestimentos.entity.User;
import com.sants.agregadorinvestimentos.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UUID createUser(CreateUserDto createUserDto) {
        // DTO -> Entity
        var entity = new User(
                UUID.randomUUID(),
                createUserDto.username(),
                createUserDto.email(),
                createUserDto.password(),
                Instant.now(),
                null);

        var userSaved = userRepository.save(entity);

        return userSaved.getUserId();
    }
}
