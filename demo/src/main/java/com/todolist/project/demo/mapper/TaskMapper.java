package com.todolist.project.demo.mapper;

import com.todolist.project.demo.dto.TaskDto;
import com.todolist.project.demo.entities.Task;

public interface TaskMapper {
    TaskDto convertToTaskDto(Task task);
}
