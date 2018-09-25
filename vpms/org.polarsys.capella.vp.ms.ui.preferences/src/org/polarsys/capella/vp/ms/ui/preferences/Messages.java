/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages {

  private static final String BUNDLE_NAME = "org.polarsys.capella.vp.ms.ui.preferences.messages"; //$NON-NLS-1$
  public static String InitializeConfigurationAccessDialog_label;
  public static String InitializeConfigurationAccessDialog_message;
  public static String InitializeConfigurationAccessDialog_title;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
  
}
