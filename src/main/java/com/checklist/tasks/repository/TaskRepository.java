package com.checklist.tasks.repository;

import com.checklist.tasks.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
