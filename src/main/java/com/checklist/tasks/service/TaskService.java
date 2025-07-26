package com.checklist.tasks.service;

import com.checklist.tasks.dto.request.TaskDto;
import com.checklist.tasks.dto.request.UserDto;
import com.checklist.tasks.model.Task;
import com.checklist.tasks.model.User;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final MapperService mapperService;

    public TaskService(MapperService mapperService) {
        this.mapperService = mapperService;
    }

    public TaskDto toDto(Task task) {
        return mapperService.map(task, TaskDto.class);
    }

    public Task toEntity(TaskDto dto) {
        return mapperService.map(dto, Task.class);
    }
}
