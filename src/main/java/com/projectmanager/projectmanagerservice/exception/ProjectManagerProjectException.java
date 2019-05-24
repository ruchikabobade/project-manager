package com.projectmanager.projectmanagerservice.exception;

public class ProjectManagerProjectException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int errorCode;
	String message;
	
//	public ProjectManagerProjectException() {}
	
	public ProjectManagerProjectException(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
	
}
