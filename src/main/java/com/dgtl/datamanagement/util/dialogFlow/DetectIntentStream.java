package com.dgtl.datamanagement.util.dialogFlow;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.BidiStream;
import com.google.cloud.dialogflow.v2.AudioEncoding;
import com.google.cloud.dialogflow.v2.InputAudioConfig;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.StreamingDetectIntentRequest;
import com.google.cloud.dialogflow.v2.StreamingDetectIntentResponse;
import com.google.protobuf.ByteString;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * DialogFlow API Detect Intent sample with audio files processes as an audio stream.
 */
class DetectIntentStream {

  static void detectIntentStream(String projectId, String audioFilePath, String sessionId) throws GeneralSecurityException {
    // String projectId = "YOUR_PROJECT_ID";
    // String audioFilePath = "path_to_your_audio_file";
    // Using the same `sessionId` between requests allows continuation of the conversation.
    // String sessionId = "Identifier of the DetectIntent session";

	  SessionsClient sessionsClient = null;
	  
    // Instantiates a client
    try {

		sessionsClient = SessionsClient.create(SessionsSettings.newBuilder()
				.setCredentialsProvider(FixedCredentialsProvider.create(DFConfig.getCredentails())).build());

		// Set the session name using the sessionId (UUID) and projectID (my-project-id)
		SessionName session = SessionName.of(DFConfig.getProjectID(), sessionId);
		System.out.println("Session Path: " + session.toString());
      
    	
      // Instructs the speech recognizer how to process the audio content.
      // Note: hard coding audioEncoding and sampleRateHertz for simplicity.
      // Audio encoding of the audio content sent in the query request.
      InputAudioConfig inputAudioConfig = InputAudioConfig.newBuilder()
          .setAudioEncoding(AudioEncoding.AUDIO_ENCODING_LINEAR_16)
          .setLanguageCode("en-US") // languageCode = "en-US"
//          .setSampleRateHertz(16000) // sampleRateHertz = 16000
          .setSampleRateHertz(8000) // sampleRateHertz = 16000
          .build();

      // Build the query with the InputAudioConfig
      QueryInput queryInput = QueryInput.newBuilder().setAudioConfig(inputAudioConfig).build();

      // Create the Bidirectional stream
      BidiStream<StreamingDetectIntentRequest, StreamingDetectIntentResponse> bidiStream =
          sessionsClient.streamingDetectIntentCallable().call();

      // The first request must **only** contain the audio configuration:
      bidiStream.send(StreamingDetectIntentRequest.newBuilder()
          .setSession(session.toString()).setSingleUtterance(true)
          .setQueryInput(queryInput)
          .build());

      try (FileInputStream audioStream = new FileInputStream(audioFilePath)) {
        // Subsequent requests must **only** contain the audio data.
        // Following messages: audio chunks. We just read the file in fixed-size chunks. In reality
        // you would split the user input by time.
        byte[] buffer = new byte[4096];
        int bytes;
        while ((bytes = audioStream.read(buffer)) != -1) {
          bidiStream.send(
              StreamingDetectIntentRequest.newBuilder()
                  .setInputAudio(ByteString.copyFrom(buffer, 0, bytes))
                  .build());
        }
      }

      // Tell the service you are done sending data
      bidiStream.closeSend();

      for (StreamingDetectIntentResponse response : bidiStream) {
        QueryResult queryResult = response.getQueryResult();
        System.out.println("====================");
        System.out.format("Intent Display Name: %s\n", queryResult.getIntent().getDisplayName());
        System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
        
        System.out.format("Detected Intent: %s (confidence: %f)\n",
            queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
        System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());
        

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
// [END dialogflow_detect_intent_streaming]