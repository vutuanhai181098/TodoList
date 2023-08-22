package com.todolist.project.demo.entities;

import com.todolist.project.demo.database.TaskDB;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    public static AtomicInteger autoId = new AtomicInteger(500);

    private Integer id;

    private String title;

    private Boolean status;

    public Task(String title) {
        this.id = autoId.incrementAndGet();
        this.title = title;
        this.status = false;
    }
}
