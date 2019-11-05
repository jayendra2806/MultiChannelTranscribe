package com.dgtl.datamanagement.util.dialogFlow;

import java.util.UUID;

import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionsClient;

public class DetectIntentStreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String projectId = DFConfig.getProjectID();
		
//		String audioFilePath = "src/main/resources/book_a_room.wav";
		String audioFilePath = "src/main/resources/OSR_us_000_0031_8k.wav";
//		String audioFilePath = "src/main/resources/16k_16PCM_eng.mp3";
		
		String languageCode = "en-US";
		
		String sessionId = UUID.randomUUID().toString();
		
		SessionsClient sessionsClient = null;
		
		try {
			DetectIntentStream.detectIntentStream(projectId, audioFilePath, sessionId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
