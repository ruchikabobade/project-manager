package com.projectmanager.projectmanagerservice.model;

import java.util.Date;

public class ProjectRecord {

	public ProjectRecord(Long projectId, String project, boolean setDate, Date startDate, Date endDate, int priority) {
		super();
		this.projectId = projectId;
		this.project = project;
		this.setDate = setDate;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
	}
	public Long projectId = null;
	public String project =  "";
	public boolean setDate = false;
	public Date startDate = null;
	public Date endDate = null;
	public int priority = 0;
}
