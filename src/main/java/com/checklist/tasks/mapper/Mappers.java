package com.checklist.tasks.mapper;


import com.checklist.tasks.dto.request.TaskDto;
import com.checklist.tasks.dto.request.UserDto;
import com.checklist.tasks.model.Task;
import com.checklist.tasks.model.User;
import com.checklist.tasks.service.MapperService;
import org.springframework.stereotype.Component;

@Component
public class Mappers {

    private final MapperService mapperService;

    public Mappers(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    public TaskDto toDtoTask(Task task) {
        return mapperService.map(task, TaskDto.class);
    }

    public Task toEntityTask(TaskDto dto) {
        return mapperService.map(dto, Task.class);
    }

    public UserDto toDtoUser(User user) {
        return mapperService.map(user, UserDto.class);
    }

    public User toEntityUser(UserDto dto) {
        return mapperService.map(dto, User.class);
    }
}
