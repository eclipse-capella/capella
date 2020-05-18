/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.patterns.ui;

import org.eclipse.osgi.util.NLS;


/**
 * Utility class for the externalization mechanism
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.patterns.ui.messages"; //$NON-NLS-1$
  public static String CapellaSemanticMapping_ShowInExplorer;
  public static String CapellaSemanticMapping_ShowInSemanticBrowser;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing
  }
}
