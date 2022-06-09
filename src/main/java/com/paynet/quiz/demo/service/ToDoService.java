package com.paynet.quiz.demo.service;

import com.paynet.quiz.demo.entities.Task;
import com.paynet.quiz.demo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoService {
    private ToDoRepository toDoRepository;

    @Autowired
    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public Task create(Task task) {
        return toDoRepository.save(task);
    }

    public Optional<Task> get(Long taskId) {
        return toDoRepository.findById(taskId);
    }

    public List<Task> list() {
        List<Task> tasks = new ArrayList<>();
        toDoRepository.findAll().forEach(task -> tasks.add(task));
        return tasks;
    }

    public Task update(Task task) {
        return toDoRepository.save(task);
    }

    public void delete(Long taskId) {
        toDoRepository.deleteById(taskId);
    }
}
