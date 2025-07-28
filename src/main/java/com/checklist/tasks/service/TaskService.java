package com.checklist.tasks.service;

import com.checklist.tasks.dto.message.TaskMessageDto;
import com.checklist.tasks.dto.request.TaskDto;
import com.checklist.tasks.dto.request.UserDto;
import com.checklist.tasks.mapper.Mappers;
import com.checklist.tasks.model.Task;
import com.checklist.tasks.model.User;
import com.checklist.tasks.repository.TaskRepository;
import com.checklist.tasks.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    @Value("${tasks.queue}")
    private String queueName;

    private final Mappers mappers;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;

    public List<Task> getTasksByUserId(String userId) {
        return taskRepository.findByUserId(userId);
    }

    public void saveTasks(TaskDto dto, String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("Usuário não localizado.");
        }

        Task task = mappers.toEntityTask(dto);
        task.setUserId(user.getId());

        taskRepository.save(task);

        TaskMessageDto taskMessageDto = mappers.toMessageDto(task);
        System.out.println("Enviando mensagem para a fila: " +
                "ID: " + taskMessageDto.getId() + ", " +
                "Title: " + taskMessageDto.getTitle() + ", " +
                "Description: " + taskMessageDto.getDescription() + ", " +
                "UserID: " + taskMessageDto.getUserId());

        try {
            rabbitTemplate.convertAndSend(queueName, taskMessageDto);
        } catch (Exception e) {
            System.out.println("Erro ao enviar mensagem para a fila: " + e.getMessage());
        }
    }


}
