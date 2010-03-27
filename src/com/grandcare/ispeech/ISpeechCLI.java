package com.grandcare.ispeech;

import java.io.File;

import com.grandcare.ispeech.request.ISpeechVoice;
import com.grandcare.ispeech.response.ISpeechConvertResponse;
import com.grandcare.ispeech.response.ISpeechDownloadResponse;
import com.grandcare.ispeech.response.ISpeechFileStatus;
import com.grandcare.ispeech.response.ISpeechInformationResponse;
import com.grandcare.ispeech.response.ISpeechStatusResponse;

public class ISpeechCLI {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		ISpeechAPI api = new ISpeechAPI(args[0], args[1]);

		System.out.println("--- Requesting info from iSpeech...");
		ISpeechInformationResponse infoResp = api.callInformation();
		if (infoResp.isSuccess()) {
			System.out.println(
					"--- You have " + infoResp.getCredits()
					+ " credits remaining.");
		} else {
			System.out.println(
					"*** ERROR: " + infoResp.getError() + " - "
					+ infoResp.getErrorMessage());
			System.exit(1);
		}

		System.out.println("--- Requesting coversion of text: " + args[3]);
		ISpeechConvertResponse convResp = 
			api.callConvert(
					args[3],
					ISpeechAudioFormat.MP3, 
					ISpeechVoice.ENGLISH_FEMALE_2);
		if(convResp.isSuccess()) {
			System.out.println(
					"--- Queued convert job " + convResp.getFileId()
					+ ", " + convResp.getWords() + " words, current status "
					+ convResp.getStatus());
		} else {
			System.out.println(
					"*** ERROR: " + convResp.getError() + " - "
					+ convResp.getErrorMessage());
			System.exit(1);
		}

		ISpeechFileId fileId = convResp.getFileId();
		ISpeechFileStatus status = convResp.getStatus();
		int eta = 1;
		
		while(status != ISpeechFileStatus.FINISHED) {
			if(status == ISpeechFileStatus.FAILED) {
				System.out.println(
						"*** ERROR: Conversion of text returned status "
						+ ISpeechFileStatus.FAILED);
				System.exit(1);
			}
			
			Thread.sleep(eta + 1000);
			
			System.out.println("--- Requesting status of convert job " + fileId);
			ISpeechStatusResponse statusResp = api.callStatus(fileId);
			if(statusResp.isSuccess()) {
				status = statusResp.getStatus();
				System.out.println(
						"--- Job is in status " + status + ", ETA "
						+ statusResp.getEta() + " seconds");
			} else {
				System.out.println(
						"*** ERROR: " + statusResp.getError() + " - "
						+ statusResp.getErrorMessage());
				System.exit(1);
			}
		}
		
		File outFile = new File(args[2]);
		
		System.out.println("--- Downloading completed file to " + outFile);
		ISpeechDownloadResponse dlResp = 
			api.callDownload(fileId, outFile);
		if(dlResp.isSuccess()) {
			System.out.println("--- Convert job complete!");
		} else {
			System.out.println(
					"*** ERROR: " + dlResp.getError() + " - "
					+ dlResp.getErrorMessage());
			System.exit(1);
		}
		

	}

}
