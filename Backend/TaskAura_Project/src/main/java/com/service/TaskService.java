package com.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.entity.TaskEntity;




/*public interface TaskService {

	
	TaskEntity createTask(TaskEntity task);

    // Update an existing task
    TaskEntity updateTask(int id, TaskEntity updatedTask);

    // Delete a task by ID
    void deleteById(int id);

    // Get all tasks
    List<TaskEntity> getAllTasks();

    // Get a task by ID
    TaskEntity getTaskById(int id);

    // Get tasks by due date
    List<TaskEntity> getTasksByDueDate(LocalDate dueDate);
    boolean isTimeSlotAvailable(LocalDate dueDate, LocalTime startTime, LocalTime endTime);
}*/





public interface TaskService {
    TaskEntity createTask(TaskEntity task);
    TaskEntity updateTask(int id, TaskEntity updatedTask);
    void deleteById(int id);
    List<TaskEntity> getAllTasks();
    TaskEntity getTaskById(int id);
    List<TaskEntity> getTasksByDueDate(LocalDate dueDate);
    
    boolean isTimeSlotAvailable(LocalDate dueDate, LocalTime startTime, LocalTime endTime);
}
   

