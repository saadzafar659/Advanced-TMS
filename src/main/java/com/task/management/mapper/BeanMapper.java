package com.task.management.mapper;

import com.task.management.entity.TaskEntity;
import com.task.management.model.TaskDto;

public class BeanMapper {

	private BeanMapper() {
		
	}
	public static TaskDto mapToDto(TaskEntity taskEntity) {
		TaskDto taskDto = new TaskDto();
		taskDto.setId(taskEntity.getId());
		taskDto.setName(taskEntity.getName());
		taskDto.setAuthor(taskEntity.getAuthor());
		taskDto.setPrice(taskEntity.getPrice());
		return taskDto;
	}

	public static TaskEntity mapToEntity(TaskDto taskDto) {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(taskDto.getId());
		taskEntity.setName(taskDto.getName());
		taskEntity.setAuthor(taskDto.getAuthor());
		taskEntity.setPrice(taskDto.getPrice());
		return taskEntity;
	}

}
