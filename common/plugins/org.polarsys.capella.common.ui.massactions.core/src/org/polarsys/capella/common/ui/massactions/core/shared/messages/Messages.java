/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.shared.messages;

import org.eclipse.osgi.util.NLS;

/**
 * 
 * @author Sandu Postaru
 *
 */
public class Messages {

  private static final String BUNDLE_NAME = "org.polarsys.capella.common.ui.massactions.core.shared.messages.messages"; //$NON-NLS-1$

  // Cell Controll text
  public static String NO_COMMON_VALUE_TEXT;
  public static String ELEMENT_SELECTION_TOOLTIP_TEXT;
  public static String MANY_REF_DIALOG_TITLE;
  public static String MANY_REF_DIALOG_MESSAGE;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Exists only to defeat instantiation.
  }

}
