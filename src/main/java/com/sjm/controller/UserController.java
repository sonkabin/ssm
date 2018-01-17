package com.sjm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjm.bean.User;
import com.sjm.service.UserService;
import com.sjm.util.MyUtil;

@Controller
public class UserController {

	@Autowired
	private UserService userService; 
	
	/*
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Message register(User user) {
		userService.saveUser(user);
		return Message.success();
	}
	*/
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(User user) {
		userService.saveUser(user);
		return "redirect:/success.jsp";
	}
	
	/*
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Message login(@RequestParam("username")String username,@RequestParam("password")String password) {
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			try {
				currentUser.login(token);
			} catch (AuthenticationException e) {
				return Message.fail().add("msg", e.getMessage());
			}
		}
		return Message.success();
	}
	*/
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("username")String username,@RequestParam("password")String password) {
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()) {
			//用户名或密码不符合要求
			if(MyUtil.StringNull(username) || MyUtil.StringNull(password))
				return "redirect:/login.jsp";
			
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			currentUser.login(token);
		}
		return "redirect:/list.jsp";
	}
}
