package com.grandcare.ispeech.request;

import com.grandcare.ispeech.ISpeechFileId;

public class ISpeechStatusRequest extends ISpeechRequest {

	public ISpeechStatusRequest(String username, String password, ISpeechFileId fileId) {
		super(username, password, ISpeechAction.STATUS);
		addParam("fileid", fileId.toParamString());
	}

}
