package com.projectmanager.projectmanagerservice.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@Column(name="project_id")
	Long projectId;
	
	@Column(name="project")
	String project;
	
	@Column(name="start_date")
	Date startDate;
	
	@Column(name="end_date")
	Date endDate;
	
	@Column(name="priority")
	int priority;	
}
