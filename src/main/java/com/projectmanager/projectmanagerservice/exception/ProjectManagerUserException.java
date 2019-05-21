package com.projectmanager.projectmanagerservice.exception;

public class ProjectManagerUserException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int errorCode;
	String message;
	
	public ProjectManagerUserException() {}
	
	public ProjectManagerUserException(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
}
