package com.todolist.project.demo.repository;

import com.todolist.project.demo.entities.Task;


import java.util.List;


public interface TaskRepository {
    List<Task> getAll();

    void save(Task task);
    void delete(Integer id);
}
