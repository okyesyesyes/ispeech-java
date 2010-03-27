package com.grandcare.ispeech.request;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

public class ISpeechRequest {
	
	private HashMap<String, String> params = new HashMap<String, String>();
	
	public ISpeechRequest(String username, String password, ISpeechAction action) {
		addParam("username", username);
		addParam("password", password);
		addParam("action", action.toParamString());
	}
	
	public void addParam(String key, String value) {
		params.put(key, value);
	}
	
	public String toRequestString() {
		StringBuffer sb = new StringBuffer();
		
		String[] keys = params.keySet().toArray(new String[params.size()]);

		sb.append(keys[0])
			.append("=")
			.append(getEncodedParam(keys[0]));
		
		for(int i = 1; i < keys.length; i++) {
			sb.append("&")
				.append(keys[i])
				.append("=")
				.append(getEncodedParam(keys[i]));
		}
		
		return sb.toString();
	}
	
	private String getEncodedParam(String key) {
		String value = params.get(key);
		try {
			return URLEncoder.encode(value, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// This will most likely never happen, but...
			return value;
		}
	}
}
