package com.todolist.project.demo;

import com.todolist.project.demo.database.TaskDB;
import com.todolist.project.demo.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private IFileReader fileReader;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TaskDB.taskList = fileReader.readFile("static/TodoDB.xlsx");
	}
}
