package com.projectmanager.projectmanagerservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="user_id")
	Long userId;
	
	@Column(name ="first_name")
	String firstName;
	
	@Column(name="last_name")
	String lastName;
	
	@Column(name="employee_id")
	String employeeId;
	
	@Column(name="project_id")
	Long projectId;
	
	@Column(name="task_id")
	Long taskId;
	
	public User() {
		
	}
	
	public User(String firstName, String lastName, String employeeId, Long projectId, Long taskId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
		this.projectId = projectId;
		this.taskId = taskId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
}
