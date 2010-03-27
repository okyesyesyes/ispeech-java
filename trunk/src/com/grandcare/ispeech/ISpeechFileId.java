package com.grandcare.ispeech;

public class ISpeechFileId {

	private String fileId;

	public ISpeechFileId(String fileId) {
		this.fileId = fileId;
	}

	public String toParamString() {
		return fileId;
	}
	
	public String toString() {
		return fileId;
	}
}
