package com.dgtl.datamanagement.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author D1 Practice
 *
 */

public class DMSIntent{
	String displayName;
    List<String> trainingPhrasesParts = new ArrayList<String>();   
    List<String> messageTexts  = new ArrayList<String>();
    List<String> inputContexts = new ArrayList<String>();   
    List<String> outputContexts = new ArrayList<String>();
	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}
	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/**
	 * @return the trainingPhrasesParts
	 */
	public List<String> getTrainingPhrasesParts() {
		return trainingPhrasesParts;
	}
	/**
	 * @param trainingPhrasesParts the trainingPhrasesParts to set
	 */
	public void setTrainingPhrasesParts(List<String> trainingPhrasesParts) {
		this.trainingPhrasesParts = trainingPhrasesParts;
	}
	/**
	 * @return the messageTexts
	 */
	public List<String> getMessageTexts() {
		return messageTexts;
	}
	/**
	 * @param messageTexts the messageTexts to set
	 */
	public void setMessageTexts(List<String> messageTexts) {
		this.messageTexts = messageTexts;
	}
	/**
	 * @return the inputContexts
	 */
	public List<String> getInputContexts() {
		return inputContexts;
	}
	/**
	 * @param inputContexts the inputContexts to set
	 */
	public void setInputContexts(List<String> inputContexts) {
		this.inputContexts = inputContexts;
	}
	/**
	 * @return the outputContexts
	 */
	public List<String> getOutputContexts() {
		return outputContexts;
	}
	/**
	 * @param outputContexts the outputContexts to set
	 */
	public void setOutputContexts(List<String> outputContexts) {
		this.outputContexts = outputContexts;
	}
    
}