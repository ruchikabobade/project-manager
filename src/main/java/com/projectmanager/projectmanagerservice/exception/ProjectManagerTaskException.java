package com.projectmanager.projectmanagerservice.exception;

public class ProjectManagerTaskException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int errorCode;
	String message;
	
//	public ProjectManagerTaskException() {}
	
	public ProjectManagerTaskException(int errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}
}
