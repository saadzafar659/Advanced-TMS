package com.task.management.service;

import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.task.management.entity.TaskEntity;
import com.task.management.mapper.BeanMapper;
import com.task.management.model.TaskDto;
import com.task.management.repository.TaskRepository;

@Service
public class TaskService {

	private TaskRepository taskRepository;

	public TaskDto getTask(Integer id) {
		TaskEntity task = taskRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("There is no Task Found with " + id));
		return BeanMapper.mapToDto(task);
	}

	public TaskDto saveTask(TaskDto task) {
		TaskEntity newTask = BeanMapper.mapToEntity(task);
		return BeanMapper.mapToDto(taskRepository.save(newTask));
	}

	public TaskDto updateTask(Integer id, TaskDto task) {
		if (!taskRepository.existsById(id)) {
			throw new NoSuchElementException("Please insert correct Id " + id);
		}
		TaskEntity updatedTask = taskRepository.save(BeanMapper.mapToEntity(task));
		return BeanMapper.mapToDto(taskRepository.save(updatedTask));
	}

	public void deleteTask(Integer id) {
		if (!taskRepository.existsById(id)) {
			throw new NoSuchElementException("Task you selected is not Present with " + id);
		}
		taskRepository.deleteById(id);
	}
}