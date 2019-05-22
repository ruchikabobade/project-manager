package com.projectmanager.projectmanagerservice.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.repository.ProjectRepository;

@Component
public class ProjectDao {
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	private ProjectRepository projectRepository;
	
	public Project addProject(ProjectRecord projectRecord) throws ProjectManagerUserException {
		logger.info("In ProjectDao, add project-----" + projectRecord.toString());
		try {
		Project project = new Project();
		project.setProject(projectRecord.project);
		if(projectRecord.setDate) {
		project.setStartDate(projectRecord.startDate);
		project.setEndDate(projectRecord.endDate);
		}
		project.setPriority(projectRecord.priority);
		return projectRepository.save(project);
		} catch(Exception ex) {
			logger.info("Exception occured while adding the project" + ex.getMessage());
			throw new ProjectManagerUserException(500, "Error in adding project");
		}
	}
	
	public Project updateProject(Project project) {
		logger.info("In ProjectDao, update project-----" + project.toString());
		return projectRepository.save(project);
	}
	
	public Project suspendProject(Long projectId) throws ProjectManagerUserException {
		logger.info("In ProjectDao, suspend project-----" + projectId);
		try {
		Project project = projectRepository.findByProjectId(projectId);
		project.setStatus(true);
		return projectRepository.save(project);
		} catch(Exception ex) {
			logger.info("Exception occured while adding the project" + ex.getMessage());
			throw new ProjectManagerUserException(500, "Error in adding project");
		}
	}
	
	public List<Project> viewProject(){
		logger.info("In ProjectDao, get project list");
		return projectRepository.findAll();
	}
	public List<Project> viewProjectByProjectName(String projectName){
		logger.info("In ProjectDao, get project list by project name ---" + projectName);
		return projectRepository.findAllByProject(projectName);
	}
	
	public Project getProject(Long projectId) {
		logger.info("In ProjectDao, get project by project id ---" + projectId);
		return  projectRepository.findByProjectId(projectId);
	}

}
