package com.sjm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjm.bean.User;
import com.sjm.bean.UserExample;
import com.sjm.dao.UserMapper;
import com.sjm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public User findUserByUP(String username, String password) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		User user = userMapper.selectByExample(example).get(0);
		return user;
	}

}
