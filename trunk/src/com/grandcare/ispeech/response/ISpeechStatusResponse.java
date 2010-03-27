package com.grandcare.ispeech.response;

import com.grandcare.ispeech.ISpeechAudioFormat;
import com.grandcare.ispeech.ISpeechException;

public class ISpeechStatusResponse extends ISpeechResponse {

	private ISpeechFileStatus status;
	private int fileSize;
	private int words;
	private int eta;
	private ISpeechAudioFormat format;
	
	public ISpeechStatusResponse(String response) throws ISpeechException {
		super(response);
		this.fileSize = Integer.parseInt(getResultValue("filesize"));
		this.status = ISpeechFileStatus.valueOf(getResultValue("status").toUpperCase());
		this.words = Integer.parseInt(getResultValue("words"));
		this.eta = Integer.parseInt(getResultValue("eta"));
		this.format = ISpeechAudioFormat.valueOf(getResultValue("format").toUpperCase());
	}

	public int getEta() {
		return eta;
	}

	public ISpeechAudioFormat getFormat() {
		return format;
	}

	public int getFileSize() {
		return fileSize;
	}

	public ISpeechFileStatus getStatus() {
		return status;
	}

	public int getWords() {
		return words;
	}
}
