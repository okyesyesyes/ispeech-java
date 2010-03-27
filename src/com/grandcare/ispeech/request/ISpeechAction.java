package com.grandcare.ispeech.request;

public enum ISpeechAction {
	INFORMATION,
	CONVERT,
	STATUS,
	DOWNLOAD;

	public String toParamString() {
		return this.name().toLowerCase();
	}
}
