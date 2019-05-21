package com.projectmanager.projectmanagerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectmanager.projectmanagerservice.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
	 public List<Task> findAllByProjectId(@Param(value = "projectId") Long projectId);
	 public List<Task> findAllByProjectIdAndStatus(@Param(value = "projectId") Long projectId,
			 @Param(value = "status") String status);
	 public Task findByTaskId(@Param(value = "taskId") Long taskId);
}
