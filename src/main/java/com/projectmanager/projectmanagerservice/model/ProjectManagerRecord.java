package com.projectmanager.projectmanagerservice.model;

import java.util.Date;

public class ProjectManagerRecord {
	
	public ProjectManagerRecord(Long taskId, String task, Date startDate, Date endDate, int priority, boolean status,
			boolean isParent, ParentTaskRecord parentTask, UserRecord user, ProjectRecord project) {
		super();
		this.taskId = taskId;
		this.task = task;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.status = status;
		this.isParent = isParent;
		this.parentTask = parentTask;
		this.user = user;
		this.project = project;
	}

	public ProjectManagerRecord() {
		// TODO Auto-generated constructor stub
	}

	public Long taskId = 0L;
	public String task = "";
	public Date startDate = null;
	public Date endDate = null;
	public int priority = 0;
	public boolean status = false;
	public boolean isParent = false;
	public ParentTaskRecord parentTask;
	public UserRecord user;
	public ProjectRecord project;
	
	@Override
	public String toString() {
		return "ProjectManagerRecord [taskId=" + taskId + ", task=" + task + ", startDate=" + startDate + ", endDate="
				+ endDate + ", priority=" + priority + ", status=" + status + ", isParent=" + isParent + ", parentTask="
				+ parentTask + ", user=" + user + ", project=" + project + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
