package com.todolist.project.demo.controller;

import com.todolist.project.demo.dto.TaskDto;
import com.todolist.project.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllList(){
        return new ResponseEntity<>(taskService.getAllList(), HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<?> getListByStatus(@RequestParam Boolean status){
        return new ResponseEntity<>(taskService.getListByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Integer id){
        return new ResponseEntity<>(taskService.getTaskById(id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.createTask(taskDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Integer id,@Valid @RequestBody TaskDto taskDto){
        return new ResponseEntity<>(taskService.updateTask(id, taskDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
