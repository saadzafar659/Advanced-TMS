package com.task.management.controller;

import java.util.List;

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
@RequestMapping("/books")
public class TaskController {

	@Autowired
	private TaskService taskService;

    @GetMapping
    public List<TaskDto> getBooks() {
        return taskService.getBooks();
    }

	@GetMapping("/{id}")
	public TaskDto getBook(@PathVariable("id") Integer id) {
		return taskService.getBook(id);
	}

	@PostMapping
	public TaskDto saveBook(@RequestBody @Valid TaskDto book) {
		return taskService.saveBook(book);
	}

	@PutMapping("/{id}")
	public TaskDto updateBook(@PathVariable("id") Integer id, @RequestBody @Valid TaskDto book) {
		if (!id.equals(book.getId())) {
			throw new IllegalArgumentException(
					String.format("Id for url (%d) and dto (%d) must be the same", id, book.getId()));
		}
		return taskService.updateBook(id, book);
	}

	@DeleteMapping("/{id}")
	public Boolean deleteBook(@PathVariable("id") Integer id) {
		taskService.deleteBook(id);
		return true;
	}
}