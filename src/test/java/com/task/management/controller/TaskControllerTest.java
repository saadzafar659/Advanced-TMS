package com.task.management.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.task.management.model.TaskDto;
import com.task.management.service.TaskService;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {
	@InjectMocks
	private TaskController controller;

	@Mock
	private TaskService service;

	private TaskDto task;
	private Integer id;
	private NoSuchElementException exception;

	@Before
	public void beforeClass() {
		id = 1;
		task = new TaskDto();
		exception = new NoSuchElementException("test exception");
	}

	@Test
	public void testGetTask() {
		when(service.getTask(any())).thenReturn(task);
		TaskDto result = controller.getTask(id);
		verify(service).getTask(any());
		assertEquals(task, result);
	}

	@Test
	public void testGetTaskNotFound() {
		when(service.getTask(any())).thenThrow(exception);
		NoSuchElementException result = assertThrows(NoSuchElementException.class, () -> controller.getTask(1));
		verify(service).getTask(any());
		assertEquals(exception.getMessage(), result.getMessage());
	}

	@Test
	public void testSaveTask() {
		when(service.saveTask(any())).thenReturn(task);
		TaskDto result = controller.saveTask(task);
		verify(service).saveTask(any());
		assertEquals(task, result);
	}

	@Test
	public void testUpdateTask() {
		task.setId(id);
		when(service.updateTask(any(), any())).thenReturn(task);
		TaskDto result = controller.updateTask(id, task);
		verify(service).updateTask(any(), any());
		assertEquals(task, result);
	}

	@Test
	public void testUpdateTaskNotFound() {
		task.setId(id);
		when(service.updateTask(any(), any())).thenThrow(exception);
		NoSuchElementException result = assertThrows(NoSuchElementException.class,
				() -> controller.updateTask(id, task));
		verify(service).updateTask(any(), any());
		assertEquals(exception.getMessage(), result.getMessage());
	}

	@Test
	public void testUpdateTaskDifferentId() {
		task.setId(id + 1);
		IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
				() -> controller.updateTask(id, task));
		verify(service, never()).updateTask(any(), any());
		assertEquals(String.format("Id for url (%d) and dto (%d) must be the same", id, task.getId()),
				result.getMessage());
	}

	@Test
	public void testDeleteTask() {
		Boolean result = controller.deleteTask(id);
		verify(service).deleteTask(any());
		assertTrue(result);
	}

	@Test
	public void testDeleteTaskNotFound() {
		doThrow(exception).when(service).deleteTask(any());
		NoSuchElementException result = assertThrows(NoSuchElementException.class, () -> controller.deleteTask(id));
		verify(service).deleteTask(any());
		assertEquals(exception.getMessage(), result.getMessage());
	}
}
