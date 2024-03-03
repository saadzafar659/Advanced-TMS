package com.task.management.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.task.management.model.TaskDto;
import com.task.management.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping("/{id}")
	public TaskDto getTask(@PathVariable("id") Integer id) {
		return taskService.getTask(id);
	}

	@PostMapping
	public TaskDto saveTask(@RequestBody @Valid TaskDto task) {
		return taskService.saveTask(task);
	}

	@PutMapping("/{id}")
	public TaskDto updateTask(@PathVariable("id") Integer id, @RequestBody @Valid TaskDto task) {
		if (!id.equals(task.getId())) {
			throw new IllegalArgumentException(
					String.format("Id for url (%d) and dto (%d) must be the same", id, task.getId()));
		}
		return taskService.updateTask(id, task);
	}

	@DeleteMapping("/{id}")
	public Boolean deleteTask(@PathVariable("id") Integer id) {
		taskService.deleteTask(id);
		return true;
	}
}