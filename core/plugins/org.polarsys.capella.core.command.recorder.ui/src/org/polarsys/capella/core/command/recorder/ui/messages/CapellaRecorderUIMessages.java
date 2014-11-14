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
package org.polarsys.capella.core.command.recorder.ui.messages;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support for this plugin
 */
public class CapellaRecorderUIMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.command.recorder.ui.messages.messages"; //$NON-NLS-1$

  public static String capellaRecorderView_column1;
  public static String capellaRecorderView_column2;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, CapellaRecorderUIMessages.class);
  }

  private CapellaRecorderUIMessages() {
    // Do nothing.
  }
}
