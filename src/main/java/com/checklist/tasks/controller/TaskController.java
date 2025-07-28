package com.checklist.tasks.controller;

import com.checklist.tasks.dto.request.TaskDto;
import com.checklist.tasks.model.Task;
import com.checklist.tasks.model.User;
import com.checklist.tasks.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDto dto,
                                        @AuthenticationPrincipal User user) {
        taskService.saveTasks(dto, user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(@AuthenticationPrincipal User user) {
        List<Task> tasks = taskService.getTasksByUserId(user.getId());
        return ResponseEntity.ok(tasks);
    }

}
