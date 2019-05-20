package com.projectmanager.projectmanagerservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.User;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	 public void suspendProject(@Param(value = "status") boolean status,
			 @Param(value = "projectId") Long projectId);

	 public Project findByProjectId(@Param(value = "projectId") Long projectId);
	 
	 public List<Project> findAllByProject(@Param(value = "project") String project);

}
