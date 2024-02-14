package com.task.management.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.management.entity.TaskEntity;
import com.task.management.mapper.BeanMapper;
import com.task.management.model.TaskDto;
import com.task.management.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public TaskDto getBook(Integer id) {
		TaskEntity book = taskRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("There is no Book Found with " + id));
		return BeanMapper.mapToDto(book);
	}

	public TaskDto saveBook(TaskDto book) {
		TaskEntity newBook = BeanMapper.mapToEntity(book);
		return BeanMapper.mapToDto(taskRepository.save(newBook));
	}

	public TaskDto updateBook(Integer id, TaskDto book) {
		if (!taskRepository.existsById(id)) {
			throw new NoSuchElementException("Please insert correct Id " + id);
		}
		TaskEntity updatedBook = taskRepository.save(BeanMapper.mapToEntity(book));
		return BeanMapper.mapToDto(taskRepository.save(updatedBook));
	}

	public void deleteBook(Integer id) {
		if (!taskRepository.existsById(id)) {
			throw new NoSuchElementException("Book you selected is not Present with " + id);
		}
		taskRepository.deleteById(id);
	}
}