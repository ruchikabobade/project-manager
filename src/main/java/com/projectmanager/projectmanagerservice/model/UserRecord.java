package com.projectmanager.projectmanagerservice.model;

public class UserRecord {

	public UserRecord(Long userId, String firstName, String lastName, String employeeId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
	}
	public Long userId = null;
	public String firstName = "";
	public String lastName = "";
	public String employeeId = "";
}
