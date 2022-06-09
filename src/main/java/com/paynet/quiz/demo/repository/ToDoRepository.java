package com.paynet.quiz.demo.repository;

import com.paynet.quiz.demo.entities.Task;
import org.springframework.data.repository.CrudRepository;

public interface ToDoRepository extends CrudRepository<Task, Long> {
}
