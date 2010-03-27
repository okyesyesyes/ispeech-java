package com.grandcare.ispeech.response;

import com.grandcare.ispeech.ISpeechException;
import com.grandcare.ispeech.ISpeechFileId;

public class ISpeechConvertResponse extends ISpeechResponse {

	private ISpeechFileId fileId;
	private ISpeechFileStatus status;
	private int words;
	
	public ISpeechConvertResponse(String response) throws ISpeechException {
		super(response);
		if(isSuccess()) {
			this.fileId = new ISpeechFileId(getResultValue("fileid"));
			this.status = ISpeechFileStatus.valueOf(getResultValue("status").toUpperCase());
			this.words = Integer.parseInt(getResultValue("words"));
		}
	}

	public ISpeechFileId getFileId() {
		return fileId;
	}

	public ISpeechFileStatus getStatus() {
		return status;
	}

	public int getWords() {
		return words;
	}
}
