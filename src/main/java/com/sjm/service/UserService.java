package com.sjm.service;

import com.sjm.bean.User;

public interface UserService {
	User findUserByUP(String username,String password);
}
