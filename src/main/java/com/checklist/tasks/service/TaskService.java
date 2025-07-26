package com.checklist.tasks.service;

import com.checklist.tasks.dto.request.TaskDto;
import com.checklist.tasks.dto.request.UserDto;
import com.checklist.tasks.mapper.Mappers;
import com.checklist.tasks.model.Task;
import com.checklist.tasks.model.User;
import com.checklist.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {

    private final Mappers mappers;
    private final TaskRepository taskRepository;


    public TaskService(Mappers mappers, TaskRepository taskRepository) {
        this.mappers = mappers;
        this.taskRepository = taskRepository;
    }


}
