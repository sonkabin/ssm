package com.sjm.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.sjm.bean.User;
import com.sjm.service.UserService;
import com.sjm.util.MD5Util;
import com.sjm.util.MyUtil;

public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;
	/**
	 * 授权会被Shiro回调的方法
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 1. 从 PrincipalCollection 中来获取登录用户的信息
		String principal = (String)principals.getPrimaryPrincipal();
		// 2. 利用登录的用户的信息来用户当前用户的角色或权限
		User user = userService.findUserByName(principal);

		//3.遍历设置用户的权限
		String role = user.getRole();
		String[] split = role.split(",");
		Set<String> roles = new HashSet<>();
		for (String string : split) {
			roles.add(string);
		}

		// 4. 创建 SimpleAuthorizationInfo, 并设置其 roles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
		return info;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2.从UsernamePasswordToken获取username
		String username = upToken.getUsername();
		String password = new String(upToken.getPassword());
		password = MD5Util.getPassword(username, password);

		// 3.调用service层方法，获取username对应的用户记录
		List<User> users = userService.findUserByUP(username, password);

		// 4.若用户不存在，抛出UnknownAccountException 异常
		if (MyUtil.ListNull(users)) {
			throw new UnknownAccountException("用户名或密码错误");
		}
		// 5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
		if ("monster".equals(username)) {
			throw new LockedAccountException("用户被锁定");
		}
		
		String realmName = getName();
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password, credentialsSalt,
				realmName);
		/*
		// 6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		// 以下信息是从数据库中获取的.
		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		Object principal = username;
		// 2). credentials: 密码.
		Object credentials = null;
		// 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		// 4). 盐值.
		ByteSource credentialsSalt = ByteSource.Util.bytes(username);
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt,
				realmName);
		*/
		return info;
	}

}
