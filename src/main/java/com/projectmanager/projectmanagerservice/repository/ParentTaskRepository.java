package com.projectmanager.projectmanagerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectmanager.projectmanagerservice.entity.ParentTask;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Long>{
	public List<ParentTask> findAllByParentTask(@Param(value = "parentTask") String parentTask);
}
