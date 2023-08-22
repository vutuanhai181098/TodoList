package com.todolist.project.demo.service;

import com.todolist.project.demo.dto.TaskDto;
import com.todolist.project.demo.entities.Task;

import java.util.List;
import java.util.Map;

public interface TaskService {
    List<TaskDto> getAllList();
    List<TaskDto> getListByStatus(Boolean status);

    TaskDto getTaskById(Integer id);

    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(Integer id, TaskDto taskDto);

    void deleteTask(Integer id);
}
