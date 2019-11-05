package com.dgtl.datamanagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.dgtl.datamanagement.model.DMSIntent;
import com.dgtl.datamanagement.util.dialogFlow.DFUtil;
import com.google.api.gax.rpc.FailedPreconditionException;

/**
 * 
 * @author VS5051403
 *
 */
@SpringBootApplication
public class DataManagement implements CommandLineRunner {
	/**
	 * It creates an appropriate ApplicationContext instance and load beans. It also
	 * runs embedded Tomcat server in Spring web application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DataManagement.class, args);
	}

	@Override
	public void run(final String... s) {
		
		  //List all intents
		  // DFUtil.getInstance().getAllIntents();
		 

//		// Create Intent
//		DMSIntent dMSIntent = new DMSIntent();
//		dMSIntent.setDisplayName("TestDMSIntent");
//		dMSIntent.getTrainingPhrasesParts().add("phrase 1");
//		dMSIntent.getTrainingPhrasesParts().add("phrase 2");
//		dMSIntent.getTrainingPhrasesParts().add("phrase 3"); 
//		dMSIntent.getTrainingPhrasesParts().add("phrase 4");
//		dMSIntent.getMessageTexts().add("messageTexts 1");
//		dMSIntent.getMessageTexts().add("messageTexts 3");
//		try {
//			DFUtil.getInstance().createIntent(dMSIntent);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		  List<String> texts = new ArrayList<String>();
		  texts.add("phrase 1"); 
		  // DFUtil.getInstance().detectIntent(texts);
		  DFUtil.getInstance().updateIntent(DFUtil.getInstance().detectIntent(texts));
		 
	}
}
