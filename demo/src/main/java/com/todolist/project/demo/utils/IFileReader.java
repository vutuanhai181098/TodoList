package com.todolist.project.demo.utils;

import com.todolist.project.demo.entities.Task;
import org.apache.poi.ss.usermodel.Cell;

import java.util.List;

public interface IFileReader {
    List<Task> readFile(String filePath);

}
