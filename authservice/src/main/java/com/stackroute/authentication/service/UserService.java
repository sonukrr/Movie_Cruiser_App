package com.stackroute.authentication.service;

import com.stackroute.authentication.exception.UserAlreadyExistsException;
import com.stackroute.authentication.exception.UserNotFoundException;
import com.stackroute.authentication.model.User;

public interface UserService {

	boolean saveUser (User user) throws UserAlreadyExistsException;
	
	public User findByUserIdAndPassword (String userId, String password) throws UserNotFoundException;
	
	
}
