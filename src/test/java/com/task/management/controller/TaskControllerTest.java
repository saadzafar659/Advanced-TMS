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
	public void testGetBook() {
		when(service.getBook(any())).thenReturn(task);
		TaskDto result = controller.getBook(id);
		verify(service).getBook(any());
		assertEquals(task, result);
	}

	@Test
	public void testGetBookNotFound() {
		when(service.getBook(any())).thenThrow(exception);
		NoSuchElementException result = assertThrows(NoSuchElementException.class, () -> controller.getBook(1));
		verify(service).getBook(any());
		assertEquals(exception.getMessage(), result.getMessage());
	}

	@Test
	public void testSaveBook() {
		when(service.saveBook(any())).thenReturn(task);
		TaskDto result = controller.saveBook(task);
		verify(service).saveBook(any());
		assertEquals(task, result);
	}

	@Test
	public void testUpdateBook() {
		task.setId(id);
		when(service.updateBook(any(), any())).thenReturn(task);
		TaskDto result = controller.updateBook(id, task);
		verify(service).updateBook(any(), any());
		assertEquals(task, result);
	}

	@Test
	public void testUpdateBookNotFound() {
		task.setId(id);
		when(service.updateBook(any(), any())).thenThrow(exception);
		NoSuchElementException result = assertThrows(NoSuchElementException.class,
				() -> controller.updateBook(id, task));
		verify(service).updateBook(any(), any());
		assertEquals(exception.getMessage(), result.getMessage());
	}

	@Test
	public void testUpdateBookDifferentId() {
		task.setId(id + 1);
		IllegalArgumentException result = assertThrows(IllegalArgumentException.class,
				() -> controller.updateBook(id, task));
		verify(service, never()).updateBook(any(), any());
		assertEquals(String.format("Id for url (%d) and dto (%d) must be the same", id, task.getId()),
				result.getMessage());
	}

	@Test
	public void testDeleteBook() {
		Boolean result = controller.deleteBook(id);
		verify(service).deleteBook(any());
		assertTrue(result);
	}

	@Test
	public void testDeleteBookNotFound() {
		doThrow(exception).when(service).deleteBook(any());
		NoSuchElementException result = assertThrows(NoSuchElementException.class, () -> controller.deleteBook(id));
		verify(service).deleteBook(any());
		assertEquals(exception.getMessage(), result.getMessage());
	}
}
