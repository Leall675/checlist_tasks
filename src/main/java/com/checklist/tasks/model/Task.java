package com.checklist.tasks.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
@Getter
@Setter
public class Task {
    @Id
    private String id;
    private String title;
    private String userId;
    private boolean validated = false;
}
