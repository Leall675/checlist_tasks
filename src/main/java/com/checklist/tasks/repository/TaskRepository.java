package com.checklist.tasks.repository;

import com.checklist.tasks.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, String> {
    boolean findByTitle(String title);
    List<Task> findByUserId(String userId);
}
