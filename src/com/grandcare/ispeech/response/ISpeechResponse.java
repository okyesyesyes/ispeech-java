package com.grandcare.ispeech.response;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import com.grandcare.ispeech.ISpeechException;

public class ISpeechResponse {
	private ISpeechError error;
	private String errorMessage;
	private HashMap<String, String> resultMap = new HashMap<String, String>();
	
	public ISpeechResponse(String response) throws ISpeechException {
	    try {
			String[] params = response.split("&");
			for (String param : params) {
			    String name = param.split("=")[0];
			    String value = param.split("=")[1];
			    resultMap.put(name, value);
			}
		} catch(Exception e) {
			throw new ISpeechException("Failed to parse iSpeech response", e);
		}
		
		String result = getResultValue("result");

		if("error".equals(result)) {
			error = ISpeechError.fromParamString(getResultValue("code"));
			errorMessage = getResultValue("message");
		} else if(!"success".equals(result)) {
			throw new ISpeechException("Malformed result string: contained unexpected 'result' value '" + result + "'");
		}
	}
	
	protected String getResultValue(String key) throws ISpeechException {
		if(resultMap.containsKey(key)) {
			try {
				return URLDecoder.decode(resultMap.get(key), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				throw new ISpeechException("Failed to decode response value", e);
			}
		} else {
			throw new ISpeechException("Malformed result string: did not contain '" + key +"' value");
		}
	}
	
	public boolean isSuccess() {
		return (error == null);
	}
	
	public ISpeechError getError() {
		return error;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
}
