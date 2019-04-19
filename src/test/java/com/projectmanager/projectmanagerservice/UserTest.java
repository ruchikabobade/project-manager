package com.projectmanager.projectmanagerservice;

import java.util.ArrayList;
import java.util.List;

import com.projectmanager.projectmanagerservice.entity.User;

public class UserTest {

	public Long userId = 1L;
	public String successMsg = "Success";
	
	public User getUserResponse() {
		User user = new User("xyz","pqr","123",1L,1L);	
		return user;
	}
	
	public List<User> getListOfUsers(){
		List<User> users = new ArrayList();
		User user1 = new User("xyz","pqr","123",1L,1L);	
		User user2 = new User("xyz","pqr","123",1L,1L);	
		users.add(user1);
		users.add(user2);
		return users;
	}
	
}
