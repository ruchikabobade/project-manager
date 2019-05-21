package com.projectmanager.projectmanagerservice.model;

import java.util.Date;

public class ParentTaskRecord {

	public ParentTaskRecord(Long parentId, String parentTask) {
		super();
		this.parentId = parentId;
		this.parentTask = parentTask;
	}
	public ParentTaskRecord() {
		// TODO Auto-generated constructor stub
	}
	public Long parentId = null;
	public String parentTask = "";
	
	@Override
	public String toString() {
		return "ParentTaskRecord [parentId=" + parentId + ", parentTask=" + parentTask + "]";
	}
}
