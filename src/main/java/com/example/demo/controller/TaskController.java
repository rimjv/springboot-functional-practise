package com.example.demo.controller;

import com.example.demo.dto.TaskRequest;
import com.example.demo.dto.TaskResponse;
import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.TaskRepository;


import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository  ;
    private final TaskService taskService ;

    public TaskController(TaskRepository taskRepository, TaskService taskService) {
        this.taskRepository = taskRepository;
        this.taskService = taskService;
    }

    @GetMapping
    public Page<TaskResponse> getAllTasks(
            @RequestParam(required = false) TaskStatus status,
            @PageableDefault(size = 10, sort = "id") Pageable pageable) {
        return taskService.getAllTasks(status, pageable);
    }
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public TaskResponse createTask(@Valid @RequestBody TaskRequest task){

            return taskService.createTask(task) ;
        }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

        @DeleteMapping("/{id}")
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public void deleteTaskById(@PathVariable Long id){
          taskService.deleteTask(id);
        }

        @PutMapping("/{id}")
        public TaskResponse updateTask(@PathVariable Long id, @Valid  @RequestBody TaskRequest  updateTask){
       return taskService.updateTask(id,updateTask) ;


        }
    }

