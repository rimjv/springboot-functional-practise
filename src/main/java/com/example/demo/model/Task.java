package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private long id;
    @NotBlank(message = "tile must not be blank")
    @Size(max = 100, message= " title must be less than 100")
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;
    public Task(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }

}
