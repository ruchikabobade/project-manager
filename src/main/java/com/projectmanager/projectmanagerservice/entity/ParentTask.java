package com.projectmanager.projectmanagerservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task")
public class ParentTask {

	@Id
	@Column(name="parent_id")
	Long parentId;
	
	@Column(name="parent_task")
	String parentTask;
}
