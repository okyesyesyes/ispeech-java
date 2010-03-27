package com.grandcare.ispeech;

public class ISpeechException extends Exception {

	private static final long serialVersionUID = 171162984739354379L;

	public ISpeechException(String message) {
		super(message);
	}

	public ISpeechException(String message, Throwable cause) {
		super(message, cause);
	}
}
