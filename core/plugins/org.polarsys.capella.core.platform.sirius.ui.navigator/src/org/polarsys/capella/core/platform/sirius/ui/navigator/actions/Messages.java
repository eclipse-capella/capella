/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.ui.navigator.actions.messages"; //$NON-NLS-1$
  public static String CloneAction_Title;
  public static String ImpactAnalysisAction_ShowInCapellaExplorer_Title;
  public static String ImpactAnalysisAction_Title;
  public static String ShowInDiagramAction_Title;
  public static String ImpactAnalysisDialog_Title;
  public static String ImpactAnalysisDialog_Message;
  public static String SortContentAction_Label;

  public static String ShowInDiagramAction_UnknownElement_Title;
  public static String ShowInDiagramAction_UnknownElement_Message;
  public static String ShowInDiagramAction_HiddenElement_Message;
  public static String ShowInDiagramAction_CollapseElement_Message;
  public static String ShowInDiagramAction_FoldedElement_Message;
  public static String ShowInDiagramAction_FilteredElement_Message;

  public static String RenameResourceAction_Session_Warning_Dialog_Title;
  public static String RenameResourceAction_Session_Warning_Dialog_Message;

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
