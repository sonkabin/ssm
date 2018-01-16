package com.sjm.util;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

	/**
	 * 工厂方法，生成FilterChainDefinitionMap，使用LinkedHashMap因为访问权限需要依次授权
	 */
	public LinkedHashMap<String, String> bulidFilterChainDefinitionMap(){
		LinkedHashMap<String, String> map = new LinkedHashMap<>();
		map.put("/login", "anon");
		map.put("/register", "anon");
		map.put("/index.jsp", "anon");
		map.put("/register.jsp", "anon");
		map.put("/login.jsp", "anon");
		map.put("/logout", "logout");
		map.put("/user.jsp","authc,roles[user]");
		map.put("/admin.jsp","authc,roles[admin]");
		map.put("/root.jsp","authc,roles[root]");	
		map.put("/**","authc");	
		
		return map;
	}
}
