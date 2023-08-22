package com.todolist.project.demo.mapper;

import com.todolist.project.demo.dto.TaskDto;
import com.todolist.project.demo.entities.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper{
    @Override
    public TaskDto convertToTaskDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setStatus(task.getStatus());
        return taskDto;
    }
}
