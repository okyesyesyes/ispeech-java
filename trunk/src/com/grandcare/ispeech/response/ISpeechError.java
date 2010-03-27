package com.grandcare.ispeech.response;

public enum ISpeechError {
	INVALID_CREDENTIALS   (1),
	FAILED_TO_QUEUE_TEXT  (2),
	INSUFFICIENT_CREDITS  (3),
	NO_ACTION             (4),
	INVALID_TEXT          (5),
	TOO_MANY_WORDS        (6),
	INVALID_TEXT_ENTRY    (7),
	INVALID_VOICE         (8),
	INVALID_HASH          (9),
	INVALID_FILE          (10),
	INVALID_FILE_ID       (11),
	INVALID_FILE_FORMAT   (12),
	INVALID_SPEED         (13),
	INVALID_DICTIONARY    (14),
	NO_API_ACCESS         (997),
	INVALID_REQUEST_METHOD(1000),
	UNDEFINED_ERROR       (9999);
	
	private int code;
	
	private ISpeechError(int code) {
		this.code = code;
	}
	
	public static ISpeechError fromParamString(String codeStr) {
		try {
			return fromCode(Integer.parseInt(codeStr));
		} catch (NumberFormatException e) {
			return UNDEFINED_ERROR;
		}
	}
	
	public static ISpeechError fromCode(int code) {
		for(ISpeechError error : ISpeechError.values()) {
			if(error.getCode() == code) {
				return error;
			}
		}
		return UNDEFINED_ERROR;
	}
	
	public int getCode() {
		return code;
	}
}
