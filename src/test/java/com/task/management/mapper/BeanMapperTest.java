package com.task.management.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.task.management.entity.TaskEntity;
import com.task.management.model.TaskDto;

@RunWith(MockitoJUnitRunner.class)
public class BeanMapperTest {

	@Test
	public void testConvertEntityToDto() {
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(1);
		taskEntity.setName("Task name");
		taskEntity.setAuthor("Task Creator");
		taskEntity.setPrice(10);

		TaskDto taskDto = BeanMapper.mapToDto(taskEntity);

		Assert.assertEquals(taskEntity.getId(), taskDto.getId());
		Assert.assertEquals(taskEntity.getName(), taskDto.getName());
		Assert.assertEquals(taskEntity.getAuthor(), taskDto.getAuthor());
		Assert.assertEquals(taskEntity.getPrice(), taskDto.getPrice());
	}

	@Test
	public void testConvertDtoToEntity() {
		TaskDto taskDto = new TaskDto();
		taskDto.setId(1);
		taskDto.setName("Task name");
		taskDto.setAuthor("Task Creator");
		taskDto.setPrice(10);

		TaskEntity taskEntity = BeanMapper.mapToEntity(taskDto);

		Assert.assertEquals(taskDto.getId(), taskEntity.getId());
		Assert.assertEquals(taskDto.getName(), taskEntity.getName());
		Assert.assertEquals(taskDto.getAuthor(), taskEntity.getAuthor());
		Assert.assertEquals(taskDto.getPrice(), taskEntity.getPrice());
	}

}
