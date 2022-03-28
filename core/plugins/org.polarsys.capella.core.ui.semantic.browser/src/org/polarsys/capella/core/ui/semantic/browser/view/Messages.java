/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.view;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.semantic.browser.view.messages"; //$NON-NLS-1$
  /**
   * Text of the label for Current Elements browser.
   */
  public static String SemanticBrowserView_Current_Element_Title;
  /**
   * The default title name for the semantic browser. This title is displayed when no element is selected.
   */
  public static String SemanticBrowserView_Default_Name;
  public static String SemanticBrowserView_ListeningToPageSelectionEventsAction_Title;
  public static String SemanticBrowserView_ListeningToPageSelectionEventsAction_Tooltip;
  public static String SemanticBrowserView_ShowPatternsAction_Tooltip;
  public static String SemanticBrowserView_ShowDiagramsAction_Tooltip;
  public static String SemanticBrowserView_LimitateTreeExpansionAction_Tooltip;
  public static String SemanticBrowserView_LexicographicSortTreeAction_Tooltip;
  
  /**
   * Text of the label for Referenced Elements browser.
   */
  public static String SemanticBrowserView_Referenced_Elements_Title;
  public static String SemanticBrowserView_Referencing_Elements_Title;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Do nothing.
  }
}
