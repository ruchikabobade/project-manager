package com.projectmanager.projectmanagerservice.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.repository.ProjectRepository;

@Component
public class ProjectDao {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project addProject(ProjectRecord projectRecord) {
		Project project = new Project();
		project.setProject(projectRecord.project);
		if(projectRecord.setDate) {
		project.setStartDate(projectRecord.startDate);
		project.setEndDate(projectRecord.endDate);
		}
		project.setPriority(projectRecord.priority);
		return projectRepository.save(project);
	}
	
	public Project updateProject(Project project) {
		return projectRepository.save(project);
	}
	
	public Project suspendProject(Long projectId) {
		Project project = projectRepository.findByProjectId(projectId);
		project.setStatus(true);
		return projectRepository.save(project);
	}
	
	public List<Project> viewProject(){
		return projectRepository.findAll();
	}
	public List<Project> viewProjectByProject(String project){
		return projectRepository.findAllByProject(project);
	}

}
