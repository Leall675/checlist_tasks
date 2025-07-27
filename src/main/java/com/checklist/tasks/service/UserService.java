package com.checklist.tasks.service;

import com.checklist.tasks.config.JwtUtils;
import com.checklist.tasks.config.security.TokenService;
import com.checklist.tasks.dto.request.LoginDto;
import com.checklist.tasks.dto.request.UserDto;
import com.checklist.tasks.dto.response.TokenDto;
import com.checklist.tasks.mapper.Mappers;
import com.checklist.tasks.model.User;
import com.checklist.tasks.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Mappers mappers;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public void save(UserDto dto) {
        if (userRepository.findByEmail(dto.getEmail()) != null) {
            throw new RuntimeException("Usuário já cadastrado.");
        }
        User user = mappers.toEntityUser(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public TokenDto login(LoginDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());
        if (user == null) {
            throw new RuntimeException("Usuário não localizado.");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Senha inválida.");
        }

        String token = tokenService.generateToken(user);
        return new TokenDto("Bearer " + token);
    }


}
