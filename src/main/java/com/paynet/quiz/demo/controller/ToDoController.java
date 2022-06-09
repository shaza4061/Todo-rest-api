package com.paynet.quiz.demo.controller;

import com.paynet.quiz.demo.entities.Task;
import com.paynet.quiz.demo.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {
    private ToDoService toDoService;

    @Autowired
    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> listTasks() {
        List<Task> tasks = toDoService.list();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> create(@RequestBody Task task) {
        Task savedTask = toDoService.create(task);
        return ResponseEntity.ok(savedTask);
    }

    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {
        Task updatedTask = toDoService.update(task);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Long taskId) {
        toDoService.delete(taskId);
    }

    @GetMapping("/task/{id}")
    public ResponseEntity<Task> getTask(@PathVariable("id") Long taskId) {
        Optional<Task> optionalTask = toDoService.get(taskId);
        if (optionalTask.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found");
        }
        return ResponseEntity.ok(optionalTask.get());
    }
}
