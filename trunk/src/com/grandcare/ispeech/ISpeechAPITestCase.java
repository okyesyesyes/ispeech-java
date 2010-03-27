package com.grandcare.ispeech;

import com.grandcare.ispeech.response.ISpeechError;
import com.grandcare.ispeech.response.ISpeechInformationResponse;

import junit.framework.TestCase;

public class ISpeechAPITestCase extends TestCase {

	public void testFailedInformationCall() throws Exception {
		ISpeechAPI api = new ISpeechAPI("foo", "bar");
		ISpeechInformationResponse resp = api.callInformation();
		assertFalse(resp.isSuccess());
		assertEquals(ISpeechError.INVALID_CREDENTIALS, resp.getError());
		assertEquals("Invalid Username or Password", resp.getErrorMessage());
	}
	
}
