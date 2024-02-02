package com.task.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.task.management.entity.TaskEntity;
import com.task.management.model.TaskDto;
import com.task.management.repository.TaskRepository;

@Controller
public class HTMLController {
    private final TaskRepository taskRepository;

    @Autowired
    public HTMLController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/saveTask")
    public String saveContact(@ModelAttribute TaskDto taskDto) {
    	TaskEntity taskEntity = new TaskEntity(
    			taskDto.getId(),
    			taskDto.getName(),
    			taskDto.getAuthor(),
    			taskDto.getPrice()

        );
        taskRepository.save(taskEntity);
        return "redirect:/";
    }
}
