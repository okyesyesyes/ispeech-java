package com.grandcare.ispeech.request;

import com.grandcare.ispeech.ISpeechFileId;

public class ISpeechDownloadRequest extends ISpeechRequest {

	public ISpeechDownloadRequest(String username, String password, ISpeechFileId fileId) {
		super(username, password, ISpeechAction.DOWNLOAD);
		addParam("fileid", fileId.toParamString());
	}

}
