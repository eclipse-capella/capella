/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.handlers;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.ui.navigator.handlers.messages"; //$NON-NLS-1$

  public static String RemoveHiddenElementsHandler_JobName;

  public static String RefreshRepresentation_0;
  public static String RefreshRepresentation_1;
  public static String RefreshRepresentation_2;
  public static String RefreshRepresentation_6;
  
  public static String RefreshRepresentation_4;
  public static String RefreshRepresentation_5;
  
  public static String RefreshRepresentation_7;
  public static String RefreshRepresentation_8;
  
  
  public static String RefreshRepresentation_9;
  public static String RefreshRepresentation_10;

  public static String GotoRelatedElementsHandler_name;
  
  public static String RemoveHiddenElementsHandler_NoDiagramDialog_Text;
  public static String RemoveHiddenElementsHandler_ConfirmRefreshDialog_Title;
  public static String RemoveHiddenElementsHandler_ConfirmRefreshDialog_Text;

  public static String resource_prefix;
  public static String refreshResultsFileName;
  public static String removeHiddenElementsResultsFileName;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
