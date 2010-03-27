package com.grandcare.ispeech.request;

import java.net.URL;

import com.grandcare.ispeech.ISpeechAudioFormat;

public class ISpeechConvertRequest extends ISpeechRequest {

	public ISpeechConvertRequest(String username, String password, String text) {
		super(username, password, ISpeechAction.CONVERT);
		addParam("text", text);
	}
	
	public void setVoice(ISpeechVoice voice) {
		addParam("voice", voice.toParamString());
	}

	public void setFormat(ISpeechAudioFormat format) {
		addParam("format", format.toParamString());
	}

	public void setTitle(String title) {
		addParam("title", title);
	}

	public void setSpeed(int speed) {
		addParam("speed", Integer.toString(speed));
	}

	public void setCallback(URL callbackUrl) {
		addParam("callback", callbackUrl.toString());
	}
}
