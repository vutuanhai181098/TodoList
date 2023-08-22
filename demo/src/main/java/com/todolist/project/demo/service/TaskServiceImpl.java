package com.todolist.project.demo.service;

import com.todolist.project.demo.dto.TaskDto;
import com.todolist.project.demo.entities.Task;
import com.todolist.project.demo.exception.ResourceNotFoundException;
import com.todolist.project.demo.mapper.TaskMapper;
import com.todolist.project.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskDto> getAllList() {
        List<Task> taskList = taskRepository.getAll();
        List<TaskDto> taskDtoList = new LinkedList<>();
        for(Task entry : taskList){
            TaskDto taskDto = taskMapper.convertToTaskDto(entry);
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public List<TaskDto> getListByStatus(Boolean status) {
        List<Task> taskList = taskRepository.getAll().stream()
                .filter(task -> task.getStatus().equals(status)).toList();
        List<TaskDto> taskDtoList = new LinkedList<>();
        for(Task entry : taskList){
            TaskDto taskDto = taskMapper.convertToTaskDto(entry);
            taskDtoList.add(taskDto);
        }
        return taskDtoList;
    }

    @Override
    public TaskDto getTaskById(Integer id) {
        Task task = taskRepository.getAll().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().orElseThrow(() -> {
                    throw new ResourceNotFoundException("Not found task");
                });

        return taskMapper.convertToTaskDto(task);
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = new Task(taskDto.getTitle());
        taskRepository.save(task);
        return taskMapper.convertToTaskDto(task);
    }

    @Override
    public TaskDto updateTask(Integer id, TaskDto taskDto) {
        Task task = taskRepository.getAll().stream()
                .filter(t -> t.getId().equals(id))
                .findFirst().orElseThrow(() -> {
                    throw new ResourceNotFoundException("Not found task");
                });
        task.setTitle(taskDto.getTitle());
        task.setStatus(taskDto.getStatus());
        return taskMapper.convertToTaskDto(task);
    }

    @Override
    public void deleteTask(Integer id) {
        taskRepository.delete(id);
    }
}
