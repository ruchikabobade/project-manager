package com.projectmanager.projectmanagerservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="task_id")
	Long taskId;
	
	@Column(name="parent_id")
	Long parentId;
	
	@Column(name="project_id")
	Long projectId;
	
	@Column(name="task")
	String task;
	
	@Column(name="start_date")
	Date startDate;
	
	@Column(name="end_date")
	Date endDate;
	
	@Column(name="priority")
	int priority;
	
	@Column(name="status")
	Boolean status;
	
	public Task() {
		
	}
	
	public Task(Long parentId, Long projectId, String task,Date startDate, Date endDate, int priority, Boolean status) {
		this.parentId = parentId;
		this.projectId = projectId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.status= status;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}
