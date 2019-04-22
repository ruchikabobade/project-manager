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
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.repository.ProjectRepository;
import com.projectmanager.projectmanagerservice.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProjectDaoTests extends ProjectManagerTest{

	@InjectMocks
	private ProjectDao projectDao;
	
	@Mock
	private ProjectRepository projectRepository;
	
	@Test
	public void test_addProject() {
		Project projectResponse = getProjectResponse();
		Mockito.when(projectRepository.save(Mockito.any(Project.class))).thenReturn(projectResponse);
		Project output = projectDao.addProject(getRecord_projectInput().project);
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
	public void test_suspendProject() {
		Mockito.doNothing().when(projectRepository).deleteById(projectId);
		String output = projectDao.suspendProject(projectId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewProject() {
		List<Project> projects = getListOfProjects();
		Mockito.when(projectRepository.findAll()).thenReturn(projects);
		List<Project> output =projectDao.viewProject();
		Assert.assertNotNull(output);
		Assert.assertEquals(projects.size(), output.size());	
	}
	
}
