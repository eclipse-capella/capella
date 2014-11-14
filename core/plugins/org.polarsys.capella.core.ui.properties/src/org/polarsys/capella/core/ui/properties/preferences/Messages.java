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
package org.polarsys.capella.core.ui.properties.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.properties.preferences.messages"; //$NON-NLS-1$

  public static String TransfertViewerPreferencePage_Title;
  public static String TransfertViewerPreferencePage_Description;

  public static String SingleTransfertViewerPreferencePage_Description;
  public static String LeftTransfertViewerPreferencePage_Description;
  public static String RightTransfertViewerPreferencePage_Description;

  public static String SingleSelectionDialogPreferencePage_Group_Title;
  public static String MultipleSelectionDialogPreferencePage_Group_Title;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
