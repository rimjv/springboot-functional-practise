package com.example.demo.repository;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TaskRepository extends JpaRepository<Task,String> {

    Page<Task> findByStatus(TaskStatus taskStatus , Pageable pageable) ;
}
