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
package org.polarsys.capella.common.command.recorder.core.messages;

import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.common.command.recorder.core.RecorderCoreActivator;

/**
 * I18n support for this plugin
 */
public class RecorderMessages extends NLS {
  private static final String BUNDLE_NAME = RecorderCoreActivator.getDefault().getPluginId() + ".messages.messages"; //$NON-NLS-1$

  public static String abstractRecorderManager_alreadyStarted;
  public static String abstractRecorderManager_alreadyStopped;

  public static String abstractRecorderManager_unknownOperation_Lbl;
  public static String abstractRecorderManager_cannotwrite;
  
  public static String recorderManagerUtils_invalidHistoryOpeningEventCode;
  public static String recorderManagerUtils_invalidHistoryClosingEventCode;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, RecorderMessages.class);
  }

  private RecorderMessages() {
    // Do nothing.
  }
}
