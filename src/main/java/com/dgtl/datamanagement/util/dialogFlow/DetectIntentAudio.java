package com.dgtl.datamanagement.util.dialogFlow;

import com.google.api.gax.core.FixedCredentialsProvider;

// Imports the Google Cloud client library

import com.google.cloud.dialogflow.v2.AudioEncoding;
import com.google.cloud.dialogflow.v2.DetectIntentRequest;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.InputAudioConfig;
import com.google.cloud.dialogflow.v2.Intent;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.protobuf.ByteString;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


/**
 * DialogFlow API Detect Intent sample with audio files.
 */
public class DetectIntentAudio {
  // [START dialogflow_detect_intent_audio]

  /**
   * Returns the result of detect intent with an audio file as input.
   *
   * Using the same `session_id` between requests allows continuation of the conversation.
   *
   * @param projectId     Project/Agent Id.
   * @param audioFilePath Path to the audio file.
   * @param sessionId     Identifier of the DetectIntent session.
   * @param languageCode  Language code of the query.
   * @return QueryResult for the request.
   */
  public static QueryResult detectIntentAudio(
      String projectId,
      String audioFilePath,
      String sessionId,
      String languageCode)
      throws Exception {
	  SessionsClient sessionsClient = null;
	  QueryResult queryResult = null;
    // Instantiates a client
    try  {
		sessionsClient = SessionsClient.create(SessionsSettings.newBuilder()
				.setCredentialsProvider(FixedCredentialsProvider.create(DFConfig.getCredentails())).build());

		// Set the session name using the sessionId (UUID) and projectID (my-project-id)
		SessionName session = SessionName.of(DFConfig.getProjectID(), sessionId);
		System.out.println("Session Path: " + session.toString());
      
      
      // Note: hard coding audioEncoding and sampleRateHertz for simplicity.
      // Audio encoding of the audio content sent in the query request.
      AudioEncoding audioEncoding = AudioEncoding.AUDIO_ENCODING_LINEAR_16;
//      int sampleRateHertz = 16000;
      int sampleRateHertz = 8000;

      // Instructs the speech recognizer how to process the audio content.
      InputAudioConfig inputAudioConfig = InputAudioConfig.newBuilder()
          .setAudioEncoding(audioEncoding) // audioEncoding = AudioEncoding.AUDIO_ENCODING_LINEAR_16
          .setLanguageCode(languageCode) // languageCode = "en-US"
          .setSampleRateHertz(sampleRateHertz) // sampleRateHertz = 16000
          .build();

      // Build the query with the InputAudioConfig
      QueryInput queryInput = QueryInput.newBuilder().setAudioConfig(inputAudioConfig).build();

      // Read the bytes from the audio file
      byte[] inputAudio = Files.readAllBytes(Paths.get(audioFilePath));

      // Build the DetectIntentRequest
      DetectIntentRequest request = DetectIntentRequest.newBuilder()
          .setSession(session.toString())
          .setQueryInput(queryInput)
          .setInputAudio(ByteString.copyFrom(inputAudio))
          .build();

      // Performs the detect intent request
      DetectIntentResponse response = sessionsClient.detectIntent(request);

      // Display the query result
      queryResult = response.getQueryResult();
      System.out.println("====================");
      System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
      System.out.format("Detected Intent: %s (confidence: %f)\n",
          queryResult.getIntent().getDisplayName(), queryResult.getIntentDetectionConfidence());
      System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());

      
    }catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} finally {
		// close the managedChannel
		sessionsClient.close();
	}
    return queryResult;
  }
  }
  // [END dialogflow_detect_intent_audio]
