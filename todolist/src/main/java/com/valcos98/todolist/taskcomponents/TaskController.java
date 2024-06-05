package com.valcos98.todolist.taskcomponents;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@CrossOrigin(origins = "http://localhost:9000")
@RequestMapping("/tasks")
public class TaskController {
    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    @PostMapping
    private ResponseEntity<Void> createANewTask(
        @RequestBody TaskModel taskModel,
        UriComponentsBuilder ucb
    ){
        TaskModel newTask = new TaskModel(taskModel.getTitle());
        TaskModel savedTask = taskRepository.saveAndFlush(newTask);
        URI locationOfNewTask = ucb
                        .path("task/{id}")
                        .buildAndExpand(savedTask.getId())
                        .toUri();
        return ResponseEntity.created(locationOfNewTask).build();
    }
    
    @GetMapping
    public ResponseEntity<List<TaskModel>> getAllTask() {
        List<TaskModel> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }
    
}
