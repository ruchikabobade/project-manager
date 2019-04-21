package com.projectmanager.projectmanagerservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.repository.ProjectRepository;

@Component
public class ProjectDao {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project addProject(Project project) {
		return projectRepository.save(project);
	}
	
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}
	
	public String suspendProject(Long projectId) {
		projectRepository.deleteById(projectId);
		return "Success";
	}
	
	public List<Project> viewProject(){
		return projectRepository.findAll();
	}

}
