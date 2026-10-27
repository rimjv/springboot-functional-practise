package com.example.demo.exception;

import com.example.demo.model.Task;

public class TaskNotFoundException extends RuntimeException{
 public TaskNotFoundException(Long id){
     super("Task not found with id" +id) ;
 }
}
