package com.example.demo.service;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public Page<TaskResponse> getAllTasks(TaskStatus status, Pageable pageable) {
        Page<Task> page = (status == null)
                ? taskRepository.findAll(pageable)
                : taskRepository.findByStatus(status, pageable);
        return page.map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new TaskNotFoundException(id));
        return toResponse(task);
    }

    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        Task task = new Task();
        applyRequest(task, request);
        return toResponse(taskRepository.save(task));
    }

    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new TaskNotFoundException(id));
        applyRequest(task, request);
        return toResponse(taskRepository.save(task));
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.delete(task);
    }

    // Copies request fields onto an entity
    private void applyRequest(Task task, TaskRequest request) {
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
    }

    // Converts an entity into a response DTO
    private TaskResponse toResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus()
        );
    }
}