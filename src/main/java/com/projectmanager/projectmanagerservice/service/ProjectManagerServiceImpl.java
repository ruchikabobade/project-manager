package com.projectmanager.projectmanagerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.projectmanagerservice.dao.ProjectDao;
import com.projectmanager.projectmanagerservice.dao.TaskDao;
import com.projectmanager.projectmanagerservice.dao.UserDao;
import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerProjectException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.ParentTaskRecord;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public User addUser(UserRecord user) throws ProjectManagerUserException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addUser() , action : adding user , data : "+  user.toString() + "}");
			return userDao.addUser(user);	
	}

	@Override
	public User updateUser(ProjectManagerRecord user) throws ProjectManagerUserException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateUser() , action : updating user , data : "+  user.toString() + "}");
		return userDao.updateUser(user);
	}

	@Override
	public String deleteUser(Long userId) throws ProjectManagerUserException{
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : deleteUser() , action : deleting user for given userId , data : "+  userId + "}");
		return userDao.deleteUser(userId);
		
	}

	@Override
	public List<User> viewUser() throws ProjectManagerUserException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewUser() , action : fetching the list of users , data :  , }");
		return userDao.viewUser();
	}
	
	@Override
	public List<User> viewUserByFirstName(String firstName) throws ProjectManagerUserException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewUserByFirstName() , action : Fetching user based on firstName  , data : "+  firstName + "}");
		return userDao.viewUserByFirstName(firstName);
	}


	@Override
	public ProjectManagerRecord addProject(ProjectManagerRecord projectManagerRecord) throws ProjectManagerUserException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addProject() , action : adding project , data : "+  projectManagerRecord.toString() + "}");
		try {
		Project project = projectDao.addProject(projectManagerRecord.project);
		projectManagerRecord.project.projectId = project.getProjectId();
		UserRecord ur = projectManagerRecord.user;
		if(ur.userId != 0) {
			userDao.updateUser(projectManagerRecord);
		}
		return projectManagerRecord;
		} catch(Exception ex) {
			logger.info("-------------------------------------------------------------");
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : addProject() , action : adding project , errorMessage : " + ex.getLocalizedMessage() + "}");
			throw new ProjectManagerUserException(500, "Error in adding project");
		}
	}

	@Override
	public Project updateProject(Project project) {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateProject() , action : updating project , data : "+  project.toString() + "}");
		return projectDao.updateProject(project);
	}

	@Override
	public Project suspendProject(Long projectId) throws ProjectManagerProjectException, ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : suspendProject() , action : suspending project based on projectId , data : "+  projectId + "}");
		try {
		Project project= projectDao.suspendProject(projectId);
		List<Task> tasks = taskDao.viewTaskByProject(project.getProjectId());
		tasks.forEach((task) -> {
			task.setStatus("completed");
			try {
			taskDao.updateTask(task);
			} catch(Exception e) {
				logger.info("-------------------------------------------------------------");
				logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : suspendProject() , action : updating the status of tasks with given projectId , errorMessage : " + e.getLocalizedMessage() + "}");
			}
		});
		return project;
		}catch(Exception ex) {
			logger.info("-------------------------------------------------------------");
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : suspendProject() , action : suspending project based on projectId , errorMessage : " + ex.getLocalizedMessage() + "}");
			throw new ProjectManagerProjectException(500, "Error in suspending project");
		}
				
	}

	@Override
	public List<ProjectRecord> viewProject() throws ProjectManagerProjectException, ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewProject() , action : fetching list of projects , data :  }");
		try {
		List<Project> projectList = projectDao.viewProject();
		List<ProjectRecord> projectRecordList = new ArrayList();
		projectList.forEach((project) ->{
			ProjectRecord projectRecord = new ProjectRecord();
			projectRecord.projectId = project.getProjectId();
			projectRecord.project = project.getProject();
			projectRecord.endDate = project.getEndDate();
			projectRecord.startDate = project.getStartDate();
			projectRecord.status = project.getStatus();
			projectRecord.priority = project.getPriority();
			projectRecord.setDate = project.isSetDate();
			User user;
			try {
				user = userDao.getUserByProjectId(project.getProjectId());
				projectRecord.manager = user.getFirstName();
			} catch (Exception e1) {
				logger.info("-------------------------------------------------------------");
				logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : viewProject() , action : fetching user for given projectId , errorMessage : " + e1.getLocalizedMessage() + "}");
			}
			
			try {
				projectRecord.tasks =taskDao.viewTaskByProject(project.getProjectId()).size();
				projectRecord.completedTasks = taskDao.getCompletedTasks(project.getProjectId()).size();
			} catch (Exception e) {
				logger.info("-------------------------------------------------------------");
				logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : viewProject() , action : fetching total tasks and completed tasks for a given projectId , errorMessage : " + e.getLocalizedMessage() + "}");
			}
			projectRecordList.add(projectRecord);		
		}) ;	
		return projectRecordList;
		} catch(Exception ex) {
			logger.info("-------------------------------------------------------------");
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : viewProject() , action : fetching project list , errorMessage : " + ex.getLocalizedMessage() + "}");
			throw new ProjectManagerProjectException(500, "Error in fetching project list");
		}
	}
	
	@Override
	public List<Project> viewProjectByProjectName(String project) {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewProjectByProjectName() , action : fetching project list based on projectName , data : "+  project + "}");
		 	return projectDao.viewProjectByProjectName(project);
	}

	@Override
	public ProjectManagerRecord addTask(ProjectManagerRecord projectManagerRecord) throws ProjectManagerUserException, ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addTask() , action : adding task , data : "+  projectManagerRecord.toString() + "}");
		try {
		if(projectManagerRecord.isParent) {
			ParentTask parentTask = taskDao.addParentTask(projectManagerRecord);
			ParentTaskRecord r = new ParentTaskRecord(parentTask.parentId, parentTask.parentTask);
			projectManagerRecord.parentTask = r ;
		}
		else {
		Task task = taskDao.addTask(projectManagerRecord);
		projectManagerRecord.taskId = task.getTaskId();
		userDao.updateUser(projectManagerRecord);
		}
        return projectManagerRecord;
		} catch(Exception ex) {
			logger.info("-------------------------------------------------------------");
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : addTask() , action : adding task , errorMessage : " + ex.getLocalizedMessage() + "}");
			throw new ProjectManagerTaskException(500, "Error in adding task");
		}
	}

	@Override
	public Task updateTask(Task task) throws ProjectManagerTaskException {	
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateTask() , action : updating task , data : "+  task.toString() + "}");
		return taskDao.updateTask(task);
	}

	@Override
	public Task endTask(Long taskId)  throws ProjectManagerTaskException  {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : endTask() , action : ending task based on taskId , data : "+  taskId + "}");
		return taskDao.endTask(taskId);
	}

	@Override
	public List<ProjectManagerRecord> viewTask()  throws ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTask() , action : fetching list of tasks , data :  }");
		try {
		List<Task> tasks = taskDao.viewTask();;
		List<ParentTask> parentTasks= taskDao.viewParentTask();
		List<ProjectManagerRecord> records =new ArrayList();
		 
		tasks.forEach((task) -> {
			ProjectManagerRecord record = new ProjectManagerRecord();
			record.taskId = task.getTaskId();
			record.project = getProjectRecord(projectDao.getProject(task.getProjectId()));
			record.endDate = task.getEndDate();
			record.startDate = task.getStartDate();
			record.priority = task.getPriority();
			record.isParent = false;
			record.task = task.getTask();
			record.status = task.getStatus();
			record.parentTask = getParentRecord(taskDao.getParentTaskById(task.getParentId()));
			records.add(record);
		});
		
		parentTasks.forEach((pt) ->{
			ProjectManagerRecord record = new ProjectManagerRecord();
			record.taskId = pt.getParentId();
			record.task = pt.getParentTask();
			record.isParent = true;
			records.add(record);
		});
		return records;
		} catch(Exception ex) {
			logger.info("-------------------------------------------------------------");
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : viewTask() , action : fetching tasks , errorMessage : " + ex.getLocalizedMessage() + "}");
			throw new ProjectManagerTaskException(500, "Error in fetching tasks");
		}
	}
	
	@Override
	public List<ProjectManagerRecord> viewTaskByProjectId(Long projectId)  throws ProjectManagerTaskException {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTaskByProject() , action : fetching list of tasks based on projectId , data : "+projectId+" }");
		try {
		List<Task> tasks = taskDao.viewTaskByProject(projectId);
		List<ProjectManagerRecord> records =new ArrayList();
		 
		tasks.forEach((task) -> {
			ProjectManagerRecord record = new ProjectManagerRecord();
			record.taskId = task.getTaskId();
			record.project = getProjectRecord(projectDao.getProject(projectId));
			record.endDate = task.getEndDate();
			record.startDate = task.getStartDate();
			record.priority = task.getPriority();
			record.isParent = false;
			record.task = task.getTask();
			record.status = task.getStatus();
			record.parentTask = getParentRecord(taskDao.getParentTaskById(task.getParentId()));
			records.add(record);
		});
		
		return records;
		} catch(Exception ex) {
			logger.info("-------------------------------------------------------------");
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" ,loggingMethod : viewTaskByProjectId() , action : fetching tasks for a given projectId , errorMessage : " + ex.getLocalizedMessage() + "}");
			throw new ProjectManagerTaskException(500, "Error in fetching tasks");
		}
	}
	@Override
	public List<ParentTask> viewTaskByParent(String parentTask) throws ProjectManagerTaskException  {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewTaskByParent() , action : fetching list of parent tasks based on taskName , data : " +parentTask+" }");
		return taskDao.viewTaskByParent(parentTask);
	}
	
	@Override
	public ProjectManagerRecord getTaskByTaskId(Long taskId) {
		logger.info("-------------------------------------------------------------");
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : getTaskById() , action : fetching task based on taskId , data : " +taskId+" }");

		ProjectManagerRecord pr = new ProjectManagerRecord();
		try {
		Task task = taskDao.getTaskById(taskId);
		pr.endDate = task.getEndDate();
		pr.startDate = task.getStartDate();
		pr.priority = task.getPriority();
		pr.task = task.getTask();
		pr.isParent = false;	
		
		} catch(NullPointerException p) {
			ParentTask t = taskDao.getParentTaskById(taskId);
			pr.task = t.getParentTask();
			pr.taskId = t.getParentId();
			pr.isParent = true;		
		}
		return pr;
	}
	
	public ProjectRecord getProjectRecord(Project project) {
		ProjectRecord pr = new ProjectRecord();
		pr.projectId = project.getProjectId();
		pr.project = project.getProject();
		return pr;
	}
 
	public ParentTaskRecord getParentRecord(ParentTask parentTask) {
		ParentTaskRecord pt = new ParentTaskRecord();
		pt.parentId = parentTask.getParentId();
		pt.parentTask = parentTask.getParentTask();
		return pt;
	}
	
}
