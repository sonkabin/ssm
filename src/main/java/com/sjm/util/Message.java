package com.sjm.util;

import java.util.HashMap;
import java.util.Map;

public class Message {

	private int code;
	private String msg;
	private Map<String, Object> info;

	public static Message success() {
		return new Message(100, "成功");
	}

	public static Message fail() {
		return new Message(200, "失败");
	}

	public Message add(String key, Object value) {
		this.info.put(key, value);
		return this;
	}

	public Message() {
		super();
	}

	public Message(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
		this.info = new HashMap<>();
	}

}
