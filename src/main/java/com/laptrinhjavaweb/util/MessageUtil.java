package com.laptrinhjavaweb.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MessageUtil {
	public Map<String, String> getMessage(String message){
		Map<String, String> result = new HashMap<>();
			if(message.equals("update_success")) {
				result.put("message", "update success");
				result.put("alert", "success");
			}else if(message.equals("insert_success")) {
				result.put("message", "insert success");
				result.put("alert", "success");
			}else if(message.equals("delete_success")) {
				result.put("message", "delete success");
				result.put("alert", "success");
			}else if(message.equals("error_system")) {
				result.put("message", "error system");
				result.put("alert", "danger");
			}
		return result;
	}

}
