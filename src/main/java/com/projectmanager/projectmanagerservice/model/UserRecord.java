package com.projectmanager.projectmanagerservice.model;

public class UserRecord {

	public UserRecord(Long userId, String firstName, String lastName, String employeeId) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.employeeId = employeeId;
	}
	public Long userId = 0l;
	public String firstName = "";
	public String lastName = "";
	public String employeeId = "";
	
	@Override
	public String toString() {
		return "UserRecord [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", employeeId="
				+ employeeId + "]";
	}
}
