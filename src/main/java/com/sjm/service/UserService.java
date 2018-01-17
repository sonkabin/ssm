package com.sjm.service;

import java.util.List;

import com.sjm.bean.User;

public interface UserService {
	List<User> findUserByUP(String username,String password);

	void saveUser(User user);
	
	User findUserByName(String username);
}
