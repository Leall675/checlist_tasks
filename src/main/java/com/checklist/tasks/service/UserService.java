package com.checklist.tasks.service;

import com.checklist.tasks.dto.request.UserDto;
import com.checklist.tasks.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final MapperService mapperService;

    public UserService(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    public UserDto toDto(User user) {
        return mapperService.map(user, UserDto.class);
    }

    public User toEntity(UserDto dto) {
        return mapperService.map(dto, User.class);
    }
}
