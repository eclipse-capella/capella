/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.vp.ms.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.vp.ms.ui.messages"; //$NON-NLS-1$
  public static String ConfigurationContentFilter_showComponents;
  public static String ConfigurationContentFilter_showFunctionalChains;
  public static String ConfigurationContentFilter_showFunctions;
  public static String ConfigurationContentFilter_showPorts;
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
