package com.spolonsk.daily.controller;

import com.spolonsk.daily.exception.ResourceNotFoundException;
import com.spolonsk.daily.model.Task;
import com.spolonsk.daily.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class TaskController {


  @Autowired
  private TaskRepository taskRepository;

  //get all tasks
  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/tasks")
  public List<Task> getAllTasks(){
    return taskRepository.findAll();
  }

  //create task
  @CrossOrigin(origins = "http://localhost:4200")
  @PostMapping("/tasks")
  public Task createTask(@RequestBody Task task){
    return taskRepository.save(task);
  }

  //get task by ID
  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/tasks/{id}")
  public ResponseEntity<Task> getTaskById(@PathVariable Long id){
    Task task = taskRepository.
      findById(id).orElseThrow(
        ()->new ResourceNotFoundException
          ("Task not found " + id));
    return ResponseEntity.ok(task);

  }

  //update existing task
  @CrossOrigin(origins = "http://localhost:4200")
  @PutMapping("/tasks/{id}")
  public ResponseEntity<Task> updateTask(
    @PathVariable Long id,@RequestBody Task newTask) {
    Task task = taskRepository.
      findById(id).orElseThrow(
        () -> new ResourceNotFoundException
          ("Task not found " + id));
    task.setName(newTask.getName());
    task.setDescription(newTask.getDescription());

    Task updatedTask = taskRepository.save(task);
    return ResponseEntity.ok(updatedTask);
  }
  //delete task
  @CrossOrigin(origins = "http://localhost:4200")
  @DeleteMapping("/tasks/{id}")
  public ResponseEntity<Map<String,Boolean>> deleteTask(@PathVariable Long id){
    Task task = taskRepository.
      findById(id).orElseThrow(
        ()->new ResourceNotFoundException
          ("Task not found " + id));

    taskRepository.delete(task);
    Map<String,Boolean> response = new HashMap<>();
    response.put("deleted",Boolean.TRUE);
    return ResponseEntity.ok(response);

  }
}
