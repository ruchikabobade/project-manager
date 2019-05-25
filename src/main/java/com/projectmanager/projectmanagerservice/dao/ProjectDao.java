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
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addProject() , action : adding project , data : "+  projectRecord.toString() + "}");
		try {
		Project project = new Project();
		project.setProjectId(projectRecord.projectId);
		project.setProject(projectRecord.project);
		if(projectRecord.setDate) {
		project.setStartDate(projectRecord.startDate);
		project.setEndDate(projectRecord.endDate);
		}
		project.setPriority(projectRecord.priority);
		return projectRepository.save(project);
		} catch(Exception ex) {
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : addProject() , action : adding project , errorMessage : " + ex.getLocalizedMessage() + "}");
			throw new ProjectManagerUserException(500, "Error in adding project");
		}
	}
	
	public Project updateProject(Project project) {
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateProject() , action : updating project , data : "+  project.toString() + "}");
		return projectRepository.save(project);
	}
	
	public Project suspendProject(Long projectId) throws ProjectManagerUserException {
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : suspendProject() , action : suspending project based on projectId , data : "+  projectId + "}");
		try {
		Project project = projectRepository.findByProjectId(projectId);
		project.setStatus(true);
		return projectRepository.save(project);
		} catch(Exception ex) {
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : suspendProject() , action : suspending project based on projectId , errorMessage : " + ex.getLocalizedMessage() + "}");
			throw new ProjectManagerUserException(500, "Error in suspending project");
		}
	}
	
	public List<Project> viewProject(){
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewProject() , action : fetching list of projects , data :  }");
		return projectRepository.findAll();
	}
	public List<Project> viewProjectByProjectName(String projectName){
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewProjectByProjectName() , action : fetching project list based on projectName , data : "+  projectName + "}");
		return projectRepository.findAllByProject(projectName);
	}
	
	public Project getProject(Long projectId) {
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : getProject() , action : fetching project based on projectId , data : "+  projectId + "}");
		return  projectRepository.findByProjectId(projectId);
	}

}
