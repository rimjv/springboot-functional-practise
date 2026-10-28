package com.example.demo.dto;

public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private String status;

    public TaskResponse() {
    }
    public TaskResponse(Long id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }
}
