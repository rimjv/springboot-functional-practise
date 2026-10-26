package com.example.demo.controller;

import com.example.demo.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.repository.TaskRepository;

import java.util.List;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskRepository taskRepository  ;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

        @GetMapping
        public List<Task> getAllTasks(){
            return taskRepository.findAll();
        }
        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Task createTask(@RequestBody Task task){
        return taskRepository.save(task);
        }

    }

