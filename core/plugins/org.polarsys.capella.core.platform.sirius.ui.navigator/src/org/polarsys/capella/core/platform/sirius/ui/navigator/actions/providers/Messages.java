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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.ui.navigator.actions.providers.messages"; //$NON-NLS-1$
  public static String OpenRepresentationActionProvider_OpenRepresentationAction_Title;
  public static String ModelElementActionProvider_Goto_Menu_Title;
  public static String NewRepresentationActionProvider_NewRepresentationAction_Title;
  public static String RenameResourceAction_Session_Warning_Dialog_Title;
  public static String RenameResourceAction_Session_Warning_Dialog_Message;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
