/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.refinement.merge.messages;

import org.eclipse.osgi.util.NLS;

/**
 * Scenario merge plugins messages. 
 */
public class MergeMessages extends NLS {
	private static final String BUNDLE_NAME = "org.polarsys.capella.core.refinement.merge.messages.messages"; //$NON-NLS-1$

	//
	// Error messages
	//
	
	public static String genericToolError;
	public static String genericInternalError;
	public static String unableToCreateMergeLinkDueToNullElt;
	public static String executionEndAESettingfailed;
	public static String strandReplacement;
	public static String mergeErrorTopMessage;
	public static String canNotPerformLevel;
	public static String unhandledIFrag;
	public static String interactionUseFeatureNotSupported;
	
	public static String cannotPerformAFullMerge;
	public static String objectRemovedDueToLackOfData;
	
	//
	// Merge Processor
	// 
	
	public static String mergeProcessorName;  
	public static String genericError;
	  
	public static String preValidationErr;
	public static String postValidationErr;

	public static String preValidationRaiseErrors;
	public static String preValidationRaiseWarnings;
	public static String preValidationRaiseInfos;
	
	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, MergeMessages.class);
	}

	/**
	 * Constructor.
	 */
	private MergeMessages() {
		// Do nothing.
	}
}
