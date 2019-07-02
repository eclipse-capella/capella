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
package org.polarsys.capella.core.platform.sirius.ui.navigator.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.ui.navigator.preferences.messages"; //$NON-NLS-1$
  public static String CapellaNavigatorPreferencePage_Description;
  public static String CapellaNavigatorPreferencePage_ProjectExplorer_Group_Message;
  public static String CapellaNavigatorPreferencePage_ProjectExplorer_Group_Title;
  public static String CapellaNavigatorPreferencePage_Title;

  public static String CapellaNavigatorPreferencePage_ProjectExplorer_PartExplicitView;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Constructor.
   */
  private Messages() {
    // Do nothing.
  }
}
