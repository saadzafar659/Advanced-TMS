package com.task.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.task.management.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

}