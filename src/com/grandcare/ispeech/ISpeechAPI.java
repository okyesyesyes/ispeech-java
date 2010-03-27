package com.grandcare.ispeech;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import com.grandcare.ispeech.request.ISpeechAction;
import com.grandcare.ispeech.request.ISpeechConvertRequest;
import com.grandcare.ispeech.request.ISpeechDownloadRequest;
import com.grandcare.ispeech.request.ISpeechRequest;
import com.grandcare.ispeech.request.ISpeechStatusRequest;
import com.grandcare.ispeech.request.ISpeechVoice;
import com.grandcare.ispeech.response.ISpeechConvertResponse;
import com.grandcare.ispeech.response.ISpeechDownloadResponse;
import com.grandcare.ispeech.response.ISpeechInformationResponse;
import com.grandcare.ispeech.response.ISpeechStatusResponse;

public class ISpeechAPI {

	private static final String ISPEECH_API_URL = "http://ws.ispeech.org/api/rest/1.5";
	
	private String username;
	private String password;

	public ISpeechAPI(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public ISpeechInformationResponse callInformation() throws ISpeechException {
		ISpeechRequest req = new ISpeechRequest(username, password, ISpeechAction.INFORMATION);
		return new ISpeechInformationResponse(callAPI(req));
	}
	
	public ISpeechConvertResponse callConvert(String text) throws ISpeechException {
		return callConvert(new ISpeechConvertRequest(username, password, text));
	}

	public ISpeechConvertResponse callConvert(
			String text, 
			ISpeechAudioFormat format, 
			ISpeechVoice voice) throws ISpeechException {
		ISpeechConvertRequest req = new ISpeechConvertRequest(username, password, text);
		req.setFormat(format);
		req.setVoice(voice);
		return callConvert(req);
	}

	public ISpeechConvertResponse callConvert(ISpeechConvertRequest req) throws ISpeechException {
		return new ISpeechConvertResponse(callAPI(req));
	}
	
	public ISpeechStatusResponse callStatus(ISpeechFileId fileId) throws ISpeechException {
		ISpeechStatusRequest req = new ISpeechStatusRequest(username, password, fileId); 
		return new ISpeechStatusResponse(callAPI(req));
	}

	public ISpeechDownloadResponse callDownload(ISpeechFileId fileId, File targetFile) throws ISpeechException {
		ISpeechDownloadResponse resp = callDownload(fileId);
		if(resp.isSuccess()) {
			try {
				byte[] buffer = new byte[8 * 1024];
				int cnt = 0;
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(targetFile));
				InputStream is = resp.getResultInputStream();
				
				while((cnt = resp.getResultInputStream().read(buffer)) != -1) {
					bos.write(buffer, 0, cnt);
				}
				
				bos.close();
				is.close();
			} catch (FileNotFoundException e) {
				throw new ISpeechException("Failed to write to file " + targetFile, e);
			} catch (IOException e) {
				throw new ISpeechException("Failed to write to file " + targetFile, e);
			}
		}
		return resp;
	}
	
	public ISpeechDownloadResponse callDownload(ISpeechFileId fileId) throws ISpeechException {
		ISpeechDownloadRequest req = new ISpeechDownloadRequest(username, password, fileId); 
		try {
			URL apiUrl = new URL(ISPEECH_API_URL);
		    
			URLConnection conn = apiUrl.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(req.toRequestString());
		    wr.flush();

		    ISpeechDownloadResponse resp = null;
		    
		    if(conn.getContentType().contains("text")) {
			    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    resp = new ISpeechDownloadResponse(rd.readLine());
			    rd.close();
		    } else {
			    resp = new ISpeechDownloadResponse(new BufferedInputStream(conn.getInputStream()));
		    }
		    
		    return resp;
		} catch (MalformedURLException e) {
			throw new ISpeechException("API URL is broken: " + ISPEECH_API_URL, e);
		} catch (IOException e) {
			throw new ISpeechException("Caught unexpected exception calling API", e);
		}
	}

	private String callAPI(ISpeechRequest req) throws ISpeechException {
		try {
			URL apiUrl = new URL(ISPEECH_API_URL);
		    
			URLConnection conn = apiUrl.openConnection();
		    conn.setDoOutput(true);
		    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		    wr.write(req.toRequestString());
		    wr.flush();

		    BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line = rd.readLine();

		    wr.close();
		    rd.close();
		    
		    return line;
		} catch (MalformedURLException e) {
			throw new ISpeechException("API URL is broken: " + ISPEECH_API_URL, e);
		} catch (IOException e) {
			throw new ISpeechException("Caught unexpected exception calling API", e);
		}
	}
}
