package com.example.demo.controller;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.model.Task;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskRepository.findById(String.valueOf(id))
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

        @DeleteMapping("/{id}")
        public ResponseEntity<Task> deleteTaskById(@PathVariable Long id){
            if(!taskRepository.existsById(String.valueOf(id)))
                return ResponseEntity.notFound().build();
        taskRepository.deleteById(String.valueOf(id));

        return ResponseEntity.noContent().build();
        }

        @PutMapping("/{id}")
        public ResponseEntity<Task> updateTask(@PathVariable Long id,@RequestBody  Task updateTask){
        return taskRepository.findById(String.valueOf(id)).map(existing -> {
                existing.setTitle(updateTask.getTitle());
                existing.setDescription(updateTask.getDescription());
                existing.setStatus(updateTask.getStatus());
                return ResponseEntity.ok(taskRepository.save(existing));
            })
                    .orElse(ResponseEntity.notFound().build());


        }
    }

