package com.sjm.handler;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sjm.service.UserService;
import com.sjm.util.Message;

@Controller
public class UserHandler {

	@Autowired
	private UserService UserService; 
	
	@RequestMapping("/login")
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
}
