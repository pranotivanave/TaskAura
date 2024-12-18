package com.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TaskRepository;
import com.entity.TaskEntity;



@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public TaskEntity createTask(TaskEntity task) {
        return taskRepository.save(task);
    }

    @Override
    public TaskEntity updateTask(int id, TaskEntity updatedTask) {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new IllegalArgumentException("Task with ID " + id + " not found.");
        }

        TaskEntity existingTask = optionalTask.get();
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setStartTime(updatedTask.getStartTime());
        existingTask.setEndTime(updatedTask.getEndTime());
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteById(int id) {
        if (!taskRepository.existsById(id)) {
            throw new IllegalArgumentException("Task with ID " + id + " does not exist.");
        }
        taskRepository.deleteById(id);
    }

    @Override
    public List<TaskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public TaskEntity getTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID " + id + " not found."));
    }

    @Override
    public List<TaskEntity> getTasksByDueDate(LocalDate dueDate) {
        return taskRepository.findByDueDate(dueDate);
    }
    
    @Override
    public boolean isTimeSlotAvailable(LocalDate dueDate, LocalTime startTime, LocalTime endTime) {
        List<TaskEntity> tasks = taskRepository.findByDueDate(dueDate);
        for (TaskEntity task : tasks) {
            if ((task.getStartTime().isBefore(endTime) && task.getEndTime().isAfter(startTime))) {
                return false; // Overlapping time
            }
        }
        return true; // No overlap
    }
}
