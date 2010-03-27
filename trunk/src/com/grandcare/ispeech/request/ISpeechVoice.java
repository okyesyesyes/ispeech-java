package com.grandcare.ispeech.request;

public enum ISpeechVoice {
	ENGLISH_FEMALE_1("engfemale1", "High Quality US english female voice"),
	ENGLISH_FEMALE_2("engfemale2", "Low Quality US english female voice"),
	ENGLISH_FEMALE_3("engfemale2", "New High Quality US english female voice"),
	ENGLISH_MALE_1  ("engmale1", "High Quality US english male voice");
	
	private String code;
	private String description;
	
	private ISpeechVoice(String code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public String toParamString() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
