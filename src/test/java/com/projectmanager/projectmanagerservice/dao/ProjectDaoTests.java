package com.projectmanager.projectmanagerservice.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.projectmanager.projectmanagerservice.ProjectManagerTest;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.repository.ProjectRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProjectDaoTests extends ProjectManagerTest{

	@InjectMocks
	private ProjectDao projectDao;
	
	@Mock
	private ProjectRepository projectRepository;
	
	@Test
	public void test_addProject() throws ProjectManagerUserException {
		Project projectResponse = getProjectResponse();
		Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(projectResponse);
		Project output = projectDao.addProject(getRecord_projectInput().project);
		Assert.assertNotNull(output);
		Assert.assertEquals(projectResponse.getProject(), output.getProject());	
	}
	
	@Test
	public void test_addProject_setDate() throws ProjectManagerUserException {
		Project projectResponse = getProjectResponse();
		ProjectRecord pr = getRecord_projectInput().project;
		pr.setDate =true;
		Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(projectResponse);
		Project output = projectDao.addProject(pr);
		Assert.assertNotNull(output);
		Assert.assertEquals(projectResponse.getProject(), output.getProject());	
	}

	@Test
	public void test_updateProject() {
		Project projectResponse = getProjectResponse();
		Mockito.when(projectRepository.save(projectResponse)).thenReturn(projectResponse);
		Project output = projectDao.updateProject(projectResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(projectResponse.getProject(), output.getProject());	
	}
	

	@Test
	public void test_suspendProject() throws ProjectManagerUserException {
		Project projectResponse = getProjectResponse();
		Mockito.when(projectRepository.findByProjectId(Mockito.any())).thenReturn(projectResponse);
		Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(projectResponse);
		Project output = projectDao.suspendProject(projectId);
		Assert.assertNotNull(output);
		Assert.assertEquals(true, output.getStatus());	
	}
	

	@Test
	public void test_viewProject() {
		List<Project> projects = getListOfProjects();
		Mockito.when(projectRepository.findAll()).thenReturn(projects);
		List<Project> output =projectDao.viewProject();
		Assert.assertNotNull(output);
		Assert.assertEquals(projects.size(), output.size());	
	}
	
	@Test
	public void test_viewProjectByProjectName() {
		List<Project> projects = getListOfProjects();
		Mockito.when(projectRepository.findAllByProject(Mockito.anyString())).thenReturn(projects);
		List<Project> output =projectDao.viewProjectByProjectName("xyz");
		Assert.assertNotNull(output);
		Assert.assertEquals(projects.size(), output.size());	
	}
	
	@Test
	public void test_getProject() {
		Project project = getProjectResponse();
		Mockito.when(projectRepository.findByProjectId(Mockito.anyLong())).thenReturn(project);
		Project output =projectDao.getProject(1L);
		Assert.assertNotNull(output);
		Assert.assertEquals(project.getProject(), output.getProject());	
	}
	
	@Test
	public void test_addProject_error() throws ProjectManagerUserException {
		Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenThrow(new RuntimeException());
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException";
		try {
			projectDao.addProject(getRecord_projectInput().project);
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
	@Test
	public void test_suspendProject_error() {
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException";
		Mockito.when(projectRepository.findByProjectId(Mockito.anyLong())).thenThrow(new RuntimeException());
		try {
			projectDao.suspendProject(projectId);
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
}
