package com.checklist.tasks.service;

import com.checklist.tasks.dto.request.UserDto;
import com.checklist.tasks.mapper.Mappers;
import com.checklist.tasks.model.User;
import com.checklist.tasks.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final Mappers mappers;
    private final UserRepository userRepository;

    public UserService(Mappers mappers, UserRepository userRepository) {
        this.mappers = mappers;
        this.userRepository = userRepository;
    }


}
