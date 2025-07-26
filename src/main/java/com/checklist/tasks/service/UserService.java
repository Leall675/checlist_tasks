package com.checklist.tasks.service;

import com.checklist.tasks.dto.request.UserDto;
import com.checklist.tasks.mapper.Mappers;
import com.checklist.tasks.model.User;
import com.checklist.tasks.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Mappers mappers;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(Mappers mappers, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.mappers = mappers;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public void save(UserDto dto) {
        if (userRepository.findByEmail(dto.getEmail()) != null) {
            throw new RuntimeException("Usuário já cadastrado.");
        }
        User user = mappers.toEntityUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
