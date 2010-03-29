package com.grandcare.ispeech.request;

import junit.framework.TestCase;

import com.grandcare.ispeech.ISpeechAudioFormat;

public class ISpeechRequestTestCase extends TestCase {

	public void testToRequestString() {
		ISpeechRequest req = new ISpeechRequest("foo", "bar", ISpeechAction.CONVERT);
		req.addParam("text", "Blah blah blah yakkity shmackity blah");
		req.addParam("voice", ISpeechVoice.ENGLISH_FEMALE_1.toParamString());
		req.addParam("format", ISpeechAudioFormat.MP3.toParamString());
		req.addParam("title", "blahblahblah");
		req.addParam("speed", "-5");
		String reqStr = req.toRequestString();
		
		assertTrue(reqStr.contains("username=foo"));
		assertTrue(reqStr.contains("password=bar"));
		assertTrue(reqStr.contains("action=convert"));
		assertTrue(reqStr.contains("text=Blah+blah+blah+yakkity+shmackity+blah"));
		assertTrue(reqStr.contains("voice=engfemale1"));
		assertTrue(reqStr.contains("format=mp3"));
		assertTrue(reqStr.contains("title=blahblahblah"));
		assertTrue(reqStr.contains("speed=-5"));
	}
}
