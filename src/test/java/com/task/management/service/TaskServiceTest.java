package com.task.management.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.task.management.entity.TaskEntity;
import com.task.management.mapper.BeanMapper;
import com.task.management.model.TaskDto;
import com.task.management.repository.TaskRepository;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

	@InjectMocks
	private TaskService service;

	@Mock
	private TaskRepository repository;

	@Mock
	private BeanMapper mapper;

	@Test
	public void testGetBook() {

		TaskEntity testTask = new TaskEntity();
		testTask.setId(1);
		testTask.setName("Test Task");
		testTask.setAuthor("Test Creator");

		// Mock the book repository's findById method to return the test book
		when(repository.findById(testTask.getId())).thenReturn(Optional.of(testTask));

		// Call the getBook method with the test book's ID
		TaskDto taskDto = service.getBook(1);

		// Verify that the returned book DTO is not null and has the correct title and
		// author
		assertNotNull(taskDto);
		assertEquals("Test Task", taskDto.getName());
		assertEquals("Test Creator", taskDto.getAuthor());
	}

	@Test
	public void testGetBook_NotFound() {
		// Arrange
		Integer id = 1;
		when(repository.findById(id)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(NoSuchElementException.class, () -> service.getBook(id));
	}

	@Test
	public void saveBook_shouldReturnSavedBook() {
		// given
		TaskDto taskDto = new TaskDto(1, "Name", "Creator", 1000);

		TaskEntity savedTaskEntity = new TaskEntity();
		savedTaskEntity.setId(1);
		savedTaskEntity.setName("Name");
		savedTaskEntity.setAuthor("Creator");
		savedTaskEntity.setPrice(1000);

		when(repository.save(any(TaskEntity.class))).thenReturn(savedTaskEntity);

		// when
		TaskDto savedBookDto = service.saveBook(taskDto);

		// then
		assertEquals(savedBookDto.getId(), savedTaskEntity.getId());
		assertEquals(savedBookDto.getName(), savedTaskEntity.getName());
		assertEquals(savedBookDto.getAuthor(), savedTaskEntity.getAuthor());
		assertEquals(savedBookDto.getPrice(), savedTaskEntity.getPrice());
	}

	@Test
	public void testUpdateBook() {
		Integer id = 1;
		TaskEntity taskEntity = new TaskEntity();
		taskEntity.setId(id);
		taskEntity.setName("Test Task");
		taskEntity.setAuthor("Test Creator");
		taskEntity.setPrice(1000);
		when(repository.existsById(id)).thenReturn(true);
		when(repository.save(any(TaskEntity.class))).thenReturn(taskEntity);
		TaskDto taskDto = new TaskDto();
		taskDto.setId(1);
		taskDto.setName("Test Task");
		taskDto.setAuthor("Test Creator");
		taskDto.setPrice(1000);
		TaskDto updatedTask = service.updateBook(id, taskDto);
		assertNotNull(updatedTask);
		assertEquals(id, updatedTask.getId());
		assertEquals("Test Task", updatedTask.getName());
		assertEquals("Test Creator", updatedTask.getAuthor());
		assertEquals(taskEntity.getPrice(), updatedTask.getPrice());
	}

	@Test(expected = NoSuchElementException.class)
	public void testUpdateBook_WhenNotExists() {
		// Arrange
		Integer id = 1;
		TaskDto input = new TaskDto();
		when(repository.existsById(id)).thenReturn(false);

		// Act
		service.updateBook(id, input);
	}

	@Test
	public void testDeleteBook_WhenExists() {
		// Arrange
		Integer id = 1;
		when(repository.existsById(id)).thenReturn(true);
		doNothing().when(repository).deleteById(id);
		// Act
		service.deleteBook(id);

		// Assert
		Mockito.verify(repository).deleteById(id);
		Mockito.verify(repository).existsById(id);
	}

	@Test(expected = NoSuchElementException.class)
	public void testDeleteBook_WhenNotExists() {
		// Arrange
		Integer id = 1;
		when(repository.existsById(id)).thenReturn(false);

		// Act
		service.deleteBook(id);

		// Act and Assert
		NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> service.deleteBook(id));
		assertEquals("Task you selected is not Present with " + id, exception.getMessage());
	}
}
