package com.todolist.project.demo.repository;

import com.todolist.project.demo.database.TaskDB;
import com.todolist.project.demo.entities.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class TaskRepositoryImpl implements TaskRepository{
    @Override
    public List<Task> getAll() {
        return TaskDB.taskList;
    }

    @Override
    public void save(Task task) {
        TaskDB.taskList.add(task);
    }

    @Override
    public void delete(Integer id) {
        TaskDB.taskList.removeIf(task -> task.getId().equals(id));
    }
}
