/**
 * 
 */
package com.dgtl.datamanagement.util.dialogFlow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.api.gax.rpc.FailedPreconditionException;
import com.google.cloud.dialogflow.v2.Intent;
import com.google.cloud.dialogflow.v2.Intent.Message;
import com.google.cloud.dialogflow.v2.Intent.Message.Text;
import com.google.cloud.dialogflow.v2.Intent.TrainingPhrase;
import com.google.cloud.dialogflow.v2.Intent.TrainingPhrase.Part;
import com.google.cloud.dialogflow.v2.IntentsClient;
import com.google.cloud.dialogflow.v2.IntentsSettings;
import com.google.cloud.dialogflow.v2.ProjectAgentName;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.SessionsSettings;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.ContextsClient;
import com.google.cloud.dialogflow.v2.DetectIntentRequest;
import com.google.cloud.dialogflow.v2.DetectIntentResponse;
import com.google.cloud.dialogflow.v2.QueryInput;
import com.google.cloud.dialogflow.v2.QueryResult;
import com.google.cloud.dialogflow.v2.SessionName;
import com.google.cloud.dialogflow.v2.SessionsClient;
import com.google.cloud.dialogflow.v2.TextInput;
import com.google.cloud.dialogflow.v2.TextInput.Builder;
import com.google.cloud.dialogflow.v2.UpdateIntentRequest;

/**
 * @author A631114
 *
 */

public class DFUtil {

	/**
	 * List intents
	 * 
	 * @param projectId Project/Agent Id.
	 */
	// static variable single_instance of type Singleton
	private static DFUtil single_instance = null;
	private Map<String, String> intentsMap = new HashMap<String, String>();
//	private String languageCode = "en-US";
	private String languageCode = "en";

	private DFUtil() {
	}

	// static method to create instance of Singleton class
	public static DFUtil getInstance() {
		if (single_instance == null)
			single_instance = new DFUtil();

		return single_instance;
	}

	/*
	 * Fetch the List of all the intents
	 * 
	 */
	public Map<String, String> getAllIntents() {

		IntentsClient intentsClient = null;
		// Instantiates a client
		try {
			intentsClient = IntentsClient.create(IntentsSettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(DFConfig.getCredentails())).build());
			// Set the project agent name using the projectID (my-project-id)
			ProjectAgentName parent = ProjectAgentName.of(DFConfig.getProjectID());

			// Performs the list intents request
			for (Intent intent : intentsClient.listIntents(parent).iterateAll()) {
				System.out.println(intent.getName() + " >>>> " + intent.getDisplayName());
				intentsMap.put(intent.getName(), intent.getDisplayName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the managedChannel
			intentsClient.close();
		}
		return intentsMap;
	}

	/**
	 * Create an intent of the given intent type
	 * 
	 * @param displayName          The display name of the intent.
	 * 
	 * @param trainingPhrasesParts Training phrases.
	 * @param messageTexts         Message texts for the agent's response when the
	 *                             intent is detected.
	 */
	public void createIntent(com.dgtl.datamanagement.model.DMSIntent dMSIntent) throws Exception {
		IntentsClient intentsClient = null;
		try {
			// Set the project agent name using the projectID (my-project-id)
			intentsClient = IntentsClient.create(IntentsSettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(DFConfig.getCredentails())).build());

			ProjectAgentName parent = ProjectAgentName.of(DFConfig.getProjectID());

			// Build the trainingPhrases from the trainingPhrasesParts
			List<TrainingPhrase> trainingPhrases = new ArrayList<>();
			for (String trainingPhrase : dMSIntent.getTrainingPhrasesParts()) {
				trainingPhrases.add(TrainingPhrase.newBuilder()
						.addParts(Part.newBuilder().setText(trainingPhrase).build()).build());
			}

			// Build the message texts for the agent's response
			Message message = Message.newBuilder()
					.setText(Text.newBuilder().addAllText(dMSIntent.getMessageTexts()).build()).build();

			// Build the intent
			Intent intent = Intent.newBuilder().setDisplayName(dMSIntent.getDisplayName()).addMessages(message)
					.addAllTrainingPhrases(trainingPhrases)
					// .addAllInputContextNames(values)
					// .addAllOutputContexts(values)
					.build();

			// Performs the create intent request
			Intent response = intentsClient.createIntent(parent, intent);
			System.out.format("DMSIntent created: %s\n", response);
		} catch (FailedPreconditionException fe) {
			System.out.println("DMSIntent Already Exists!!!");
			// TODO: handle exception
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Close the managedChannel
			intentsClient.close();
		}
	}

	/*
	 * Detect Intent
	 */
	public Intent detectIntent(List<String> texts) {
		String sessionId = UUID.randomUUID().toString();
		Intent intent = null;
		SessionsClient sessionsClient = null;
		// Instantiates a client
		try {

			sessionsClient = SessionsClient.create(SessionsSettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(DFConfig.getCredentails())).build());

			// Set the session name using the sessionId (UUID) and projectID (my-project-id)
			SessionName session = SessionName.of(DFConfig.getProjectID(), sessionId);
			System.out.println("Session Path: " + session.toString());

			// Detect intents for each text input
			for (String text : texts) {
				// Set the text (hello) and language code (en-US) for the query
				Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

				// Build the query with the TextInput
				QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

				// Performs the detect intent request
				DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

				// Display the query result
				QueryResult queryResult = response.getQueryResult();

				System.out.println("====================");
				System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
				System.out.format("Display Name: '%s'\n", queryResult.getIntent().getDisplayName());
				System.out.format("Detected Intent: %s (confidence: %f)\n", queryResult.getIntent().getDisplayName(),
						queryResult.getIntentDetectionConfidence());
				System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());
				intent = queryResult.getIntent();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// close the managedChannel
			sessionsClient.close();
		}
		return intent;
	}

	/*
	 * Update the response of an existing Intent
	 */
	public void updateIntent(Intent intent) {
		IntentsClient intentsClient = null;
		try {
			
			// Set the project agent name using the projectID (my-project-id)
			intentsClient = IntentsClient.create(IntentsSettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(DFConfig.getCredentails())).build());
			
//			intent = Intent.newBuilder().setDisplayName("testing").build();
			UpdateIntentRequest request = UpdateIntentRequest.newBuilder().setIntent(intent)
					.setLanguageCode(languageCode).build();
			Intent response = intentsClient.updateIntent(request);
			System.out.format("DMSIntent updated: %s\n", response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// Close the managedChannel
			intentsClient.close();
		}

	}
	
	/*
	 * Detect Intent
	 */
	public Intent detectIntent(String text) {
		String sessionId = UUID.randomUUID().toString();
		Intent intent = null;
		SessionsClient sessionsClient = null;
		// Instantiates a client
		try {

			sessionsClient = SessionsClient.create(SessionsSettings.newBuilder()
					.setCredentialsProvider(FixedCredentialsProvider.create(DFConfig.getCredentails())).build());

			// Set the session name using the sessionId (UUID) and projectID (my-project-id)
			SessionName session = SessionName.of(DFConfig.getProjectID(), sessionId);
			System.out.println("Session Path: " + session.toString());

			// Detect intents for each text input
//			for (String text : texts) {
				// Set the text (hello) and language code (en-US) for the query
				Builder textInput = TextInput.newBuilder().setText(text).setLanguageCode(languageCode);

				// Build the query with the TextInput
				QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();

				// Performs the detect intent request
				DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);

				// Display the query result
				QueryResult queryResult = response.getQueryResult();

				System.out.println("====================");
				System.out.format("Query Text: '%s'\n", queryResult.getQueryText());
				System.out.format("Display Name: '%s'\n", queryResult.getIntent().getDisplayName());
				System.out.format("Detected Intent: %s (confidence: %f)\n", queryResult.getIntent().getDisplayName(),
						queryResult.getIntentDetectionConfidence());
				System.out.format("Fulfillment Text: '%s'\n", queryResult.getFulfillmentText());
				intent = queryResult.getIntent();
//			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// close the managedChannel
			sessionsClient.close();
		}
		return intent;
	}

	
	
}
