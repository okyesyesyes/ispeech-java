package com.grandcare.ispeech.response;

import com.grandcare.ispeech.ISpeechException;

public class ISpeechInformationResponse extends ISpeechResponse {

	private int credits;
	
	public ISpeechInformationResponse(String response) throws ISpeechException {
		super(response);
		if(isSuccess()) {
			this.credits = Integer.parseInt(getResultValue("credits"));
		}
	}

	public int getCredits() {
		return credits;
	}
}
