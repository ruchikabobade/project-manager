package com.projectmanager.projectmanagerservice.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerProjectException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;
import com.projectmanager.projectmanagerservice.service.ProjectManagerService;

@RestController
@CrossOrigin(origins = {"*"})
public class ProjectManagerController {

	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	private ProjectManagerService service;
	
	@PostMapping(path="/projectmanager/service/user/addUser")
	public User addUser(@RequestBody UserRecord user ) throws ProjectManagerUserException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addUser() , action : adding user , data : "+  user.toString() + "}");
		return service.addUser(user);
	}
	
	@PutMapping(path="/projectmanager/service/user/updateUser")
	public User updateUser(@RequestBody ProjectManagerRecord user)  throws ProjectManagerUserException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateUser() , action : updating user , data : "+  user.toString() + "}");
		return service.updateUser(user);
	}
	
	@DeleteMapping(path="/projectmanager/service/user/deleteUser/{userId}")
	public String deleteUser(@PathVariable Long userId) throws ProjectManagerUserException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : deleteUser() , action : deleting user for given userId , data : "+  userId + "}");
		return service.deleteUser(userId);
	}
	
	@GetMapping(path="/projectmanager/service/user/viewUser")
	public List<User> viewUser() throws ProjectManagerUserException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewUser() , action : fetching the list of users , data :  , }");
		return service.viewUser();
	}
	

	@GetMapping(path="/projectmanager/service/user/viewUserByFirstName/{firstName}")
	public List<User> viewUserByFirstName(@PathVariable String firstName) throws ProjectManagerUserException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewUserByFirstName() , action : Fetching user based on firstName  , data : "+  firstName + "}");
		return service.viewUserByFirstName(firstName);
	}
	
	@PostMapping(path="/projectmanager/service/project/addProject")
	public ProjectManagerRecord addProject(@RequestBody ProjectManagerRecord project ) throws ProjectManagerUserException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addProject() , action : adding project , data : "+  project.toString() + "}");
		return service.addProject(project);
	}
	
	@PutMapping(path="/projectmanager/service/project/updateProject")
	public ProjectManagerRecord updateProject(@RequestBody ProjectManagerRecord project) throws ProjectManagerUserException, ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateProject() , action : updating project , data : "+  project.toString() + "}");
		return service.updateProject(project);
	}
	
	@DeleteMapping(path="/projectmanager/service/project/suspendProject/{projectId}")
	public Project suspendProject(@PathVariable Long projectId) throws ProjectManagerProjectException, ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : suspendProject() , action : suspending project based on projectId , data : "+  projectId + "}");
		return service.suspendProject(projectId);
	}
	
	@GetMapping(path="/projectmanager/service/project/viewProject")
	public List<ProjectRecord> viewProject() throws ProjectManagerProjectException, ProjectManagerTaskException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewProject() , action : fetching list of projects , data :  }");
		return service.viewProject();
	}
	
	@GetMapping(path="/projectmanager/service/project/viewProjectByProject/{project}")
	public List<Project> viewProjectByProjectName(@PathVariable String project){
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewProjectByProjectName() , action : fetching project list based on projectName , data : "+  project + "}");
		return service.viewProjectByProjectName(project);
	}
	
	@PostMapping(path="/projectmanager/service/task/addtask")
	public ProjectManagerRecord addTask(@RequestBody ProjectManagerRecord task ) throws ProjectManagerUserException, ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addTask() , action : adding task , data : "+  task.toString() + "}");
		return service.addTask(task);
	}
	
	@PutMapping(path="/projectmanager/service/task/updateTask")
	public ProjectManagerRecord updateTask(@RequestBody ProjectManagerRecord task) throws ProjectManagerTaskException, ProjectManagerUserException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateTask() , action : updating task , data : "+  task.toString() + "}");
		return service.updateTask(task);
	}
	
	@DeleteMapping(path="/projectmanager/service/task/endTask/{taskId}")
	public Task endTask(@PathVariable Long taskId) throws ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : endTask() , action : ending task based on taskId , data : "+  taskId + "}");
		return service.endTask(taskId);
	}
	
	@GetMapping(path="/projectmanager/service/task/viewTask")
	public List<ProjectManagerRecord> viewTask() throws ProjectManagerTaskException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTask() , action : fetching list of tasks , data :  }");
		return service.viewTask();
	}
	
	@GetMapping(path="/projectmanager/service/task/viewTaskByParent/{parentTask}")
	public List<ParentTask> viewTaskByParent(@PathVariable String parentTask) throws ProjectManagerTaskException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTaskByParent() , action : fetching list of parent tasks based on taskName , data : " +parentTask+" }");
		return service.viewTaskByParent(parentTask);
	}
	
	@GetMapping(path="/projectmanager/service/task/viewTaskById/{taskId}")
	public ProjectManagerRecord viewTaskById(@PathVariable Long taskId) throws ProjectManagerTaskException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTaskById() , action : fetching task based on taskId , data : " +taskId+" }");
		return service.getTaskByTaskId(taskId);
	}
	
	@GetMapping(path="/projectmanager/service/task/viewTaskByProjectId/{projectId}")
	public List<ProjectManagerRecord> viewTaskByProject(@PathVariable Long projectId) throws ProjectManagerTaskException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTaskByProject() , action : fetching list of tasks based on projectId , data : "+projectId+" }");
		return service.viewTaskByProjectId(projectId);
	}
	
	@GetMapping(path="/projectmanager/service/task/viewParentTask")
	public List<ParentTask> viewParentTask() throws ProjectManagerTaskException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTaskByParent() , action : fetching list of parent tasks based on taskName , data :  }");
		return service.viewParentTask();
	}
}
