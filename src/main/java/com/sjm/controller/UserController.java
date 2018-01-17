package com.sjm.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sjm.bean.User;
import com.sjm.service.UserService;
import com.sjm.util.Message;

@Controller
public class UserController {

	@Autowired
	private UserService userService; 
	
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public Message register(User user) {
		userService.saveUser(user);
		return Message.success();
	}
	
	/*
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String register(User user) {
		userService.saveUser(user);
		return "redirect:/success.jsp";
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public Message login(@RequestParam("username")String username,@RequestParam("password")String password) {
		Subject currentUser = SecurityUtils.getSubject();
		if(!currentUser.isAuthenticated()) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			
			currentUser.login(token);
		}
		return Message.success();
	}
	
	
	/*
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
	*/
}
