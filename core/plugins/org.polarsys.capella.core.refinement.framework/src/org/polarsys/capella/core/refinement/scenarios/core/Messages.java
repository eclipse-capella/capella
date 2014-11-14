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
package org.polarsys.capella.core.refinement.scenarios.core;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.refinement.scenarios.core.messages"; //$NON-NLS-1$

  public static String PreProcessing_Progress;
  public static String PreProcessing_Succeeded;
  
  public static String Processing_Succeeded;
  public static String Processing_Failed;

  public static String PostProcessing_Progress;
  public static String PostProcessing_Succeeded;

  public static String NewPreProcessorProvider;
  public static String NewMapperProvider;
  public static String NewResolverProvider;
  public static String NewSchedulerProvider;
  public static String NewPostProcessorProvider;
  public static String NewListenerProvider;

  public static String ScenarioRefinement_Progress;
  public static String ScenarioRefinement_Succeeded;
  public static String ScenarioRefinement_Failed;

  public static String ErrorMessageEndNotLinkedToSequenceMessage;
  public static String ErrorInstanceNotTyped;
  public static String ErrorInstanceRoleNotLinked;
  public static String ErrorAbstractEndNotLinkedToInstanceRole;

  public static String SkeepSequenceMessageMapping;
  public static String SkeepSequenceMessageReplyMapping;
  public static String TryMapSequenceMessageSender;
  public static String TryMapSequenceMessageReplySender;
  public static String TryMapSequenceMessageReceiver;
  public static String TryMapSequenceMessageReplyReceiver;
  public static String UnmappedSequenceMessageSender;
  public static String UnmappedSequenceMessageReplySender;
  public static String UnmappedSequenceMessageReceiver;
  public static String UnmappedSequenceMessageReplyReceiver;
  
  public static String DebugScenarioContent;
  
  public static String CreationEventNamePattern;
  public static String EventReceiptOperationNamePattern;
  public static String EventSentOperationNamePattern;
  public static String MessageEndReceiverNamePattern;
  public static String MessageEndSenderNamePattern;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Do nothing.
  }
}
