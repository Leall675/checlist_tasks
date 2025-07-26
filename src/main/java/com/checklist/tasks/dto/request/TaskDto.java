package com.checklist.tasks.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskDto {
    private String id;
    @NotBlank(message = "O titulo da tarefa é obrigatório.")
    private String title;
    private String description;
    private String userId;
}
