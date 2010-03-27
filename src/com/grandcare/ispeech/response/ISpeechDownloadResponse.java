package com.grandcare.ispeech.response;

import java.io.InputStream;

import com.grandcare.ispeech.ISpeechException;

public class ISpeechDownloadResponse extends ISpeechResponse {

	private InputStream resultInputStream;
	
	public ISpeechDownloadResponse(String response) throws ISpeechException {
		super(response);
	}
	
	public ISpeechDownloadResponse(InputStream is) throws ISpeechException {
		super("result=success");
		this.resultInputStream = is;
	}

	public InputStream getResultInputStream() {
		return resultInputStream;
	}
}
