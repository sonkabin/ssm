package com.sjm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjm.bean.User;
import com.sjm.bean.UserExample;
import com.sjm.dao.UserMapper;
import com.sjm.service.UserService;
import com.sjm.util.MD5Util;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	/**
	 * 通过用户名和经过加密运算后的密码查询数据库
	 */
	@Override
	public List<User> findUserByUP(String username, String password) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
		List<User> users = userMapper.selectByExample(example);
		return users;
	}

	@Override
	public void saveUser(User user) {
		//获取加密后的密码
		String credentials = MD5Util.getPassword(user.getUsername(),user.getPassword());
		
		user.setPassword(credentials);
		userMapper.insertSelective(user);
	}

	@Override
	public User findUserByName(String username) {
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(username);
		User user = userMapper.selectByExample(example).get(0);
		return user;
	}

}
