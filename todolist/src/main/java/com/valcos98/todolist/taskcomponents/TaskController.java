package com.valcos98.todolist.taskcomponents;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping("/tasks")
public class TaskController {
    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @CrossOrigin(exposedHeaders = "Location")
    @PostMapping
    private ResponseEntity<Void> createANewTask(
        @RequestBody TaskModel taskModel,
        UriComponentsBuilder ucb
    ){
        TaskModel newTask = new TaskModel(taskModel.getTitle());
        TaskModel savedTask = taskRepository.saveAndFlush(newTask);
        URI locationOfNewTask = ucb
                        .path("tasks/{id}")
                        .buildAndExpand(savedTask.getId())
                        .toUri();
        return ResponseEntity.created(locationOfNewTask).build();
    }
    
    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTask() {
        List<TaskModel> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }
    
    @GetMapping(path = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskModel> getATask(@PathVariable Long requestId) {
        TaskModel task = taskRepository.findById(requestId).get();
        if (task != null) {
            return ResponseEntity.ok(task);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    
}
