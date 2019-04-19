package com.projectmanager.projectmanagerservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="task")
public class Task {

	@Id
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
	String priority;
	
	@Column(name="status")
	String status;
}
