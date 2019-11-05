package com.dgtl.datamanagement.util.dialogFlow;

import java.util.UUID;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.cloud.dialogflow.v2.Intent;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;

public class DetectIntentAudioTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String projectId = DFConfig.getProjectID();
		
//		String audioFilePath = "src/main/resources/book_a_room.wav";
		String audioFilePath = "src/main/resources/OSR_us_000_0031_8k.wav";
		
		String languageCode = "en-US";
		
		QueryResult result = null;
		
		String sessionId = UUID.randomUUID().toString();
		
		SessionsClient sessionsClient = null;
		
		try {
			result = DetectIntentAudio.detectIntentAudio(projectId, audioFilePath, sessionId, languageCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("result ... "+ result);
		
	}

}
