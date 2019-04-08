package com.stackroute.authentication.exception;

@SuppressWarnings("serial")
public class UserAlreadyExistsException extends Exception{

	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public UserAlreadyExistsException() {
		// TODO Auto-generated constructor stub
	}
	
	public UserAlreadyExistsException(final String message){
		super(message);
		this.message = message;
	}

	@Override
	public String toString() {
		return "UserAlreadyExistsException [message=" + message + "]";
	}
	
	
}
