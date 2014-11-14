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
package org.polarsys.capella.core.ui.toolkit.helpers;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.toolkit.helpers.messages"; //$NON-NLS-1$
  public static String AffectToMessage_SelectionOperationDialog_Message;
  public static String AffectToMessage_SelectionExchangeDialog_Message;
  public static String AffectToMessage_SelectionInteractionDialog_Message;
  public static String AffectToMessage_SelectionComponentExchangeDialog_Message;
  public static String AffectToMessage_SelectionCommunicationMeanDialog_Message;
  public static String SelectionDialogHelper_MessageCreation_Title;

  public static String SelectionDialogHelper_SelectionWizard_Title;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
