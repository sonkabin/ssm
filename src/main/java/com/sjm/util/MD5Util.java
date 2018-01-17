package com.sjm.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 密码计算工具类
 * @author sonkabin
 *
 */
public class MD5Util {

	//计算MD5加密后的密码后返回
	public static String getPassword(String name,String password) {
		String hashAlgorithmName = "MD5";
		Object credentials = password;
		Object salt = ByteSource.Util.bytes(name);
		int hashIterations = 1024;
		
		SimpleHash result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result.toString());
		return result.toString();
	}
	
	public static void main(String[] args) {
		getPassword("admin","123456");
	}
}
