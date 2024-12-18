package com.controller;

import java.time.LocalDate;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

import com.entity.TaskEntity;
import com.service.TaskService;

//@CrossOrigin(origins = "http://localhost:4200") 


@RestController

@RequestMapping("/tasks")
@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    @Autowired
    private TaskService taskService;

    // Create a new task
    /*@PostMapping
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
        TaskEntity createdTask = taskService.createTask(task);
        return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
    }*/
    
   
    
    @PostMapping
    public ResponseEntity<String> createTask(@RequestBody TaskEntity task) {
        if (!taskService.isTimeSlotAvailable(task.getDueDate(), task.getStartTime(), task.getEndTime())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .header(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8")
                    .body("Task with overlapping time already exists for the same day.");
        }
        TaskEntity createdTask = taskService.createTask(task);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.CONTENT_TYPE, "text/plain; charset=utf-8")
                .body("Task created successfully with ID: " + createdTask.getId());
    }

    // Update an existing task by ID
    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable int id, @RequestBody TaskEntity task) {
        TaskEntity updatedTask = taskService.updateTask(id, task);
        return new ResponseEntity<>(updatedTask, HttpStatus.OK);
    }

    // Delete a task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable int id) {
        taskService.deleteById(id);
        return new ResponseEntity<>("Task with ID " + id + " deleted successfully", HttpStatus.OK);
    }

    // Get tasks by due date
    @GetMapping("/dueDate/{date}")
    public ResponseEntity<List<TaskEntity>> getTasksByDueDate(@PathVariable String date) {
        LocalDate dueDate = LocalDate.parse(date);
        List<TaskEntity> tasks = taskService.getTasksByDueDate(dueDate);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        List<TaskEntity> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    // Get a task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable int id) {
        TaskEntity task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }
}


