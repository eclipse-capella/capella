/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
  public static String SearchOptions_Menu_Title;
  public static String SearchOptions_Case_Titlte;
  public static String SearchOptions_Case_Tooltip;
  public static String SearchOptions_InDesc_Title;
  public static String SearchOptions_InDesc_Tooltip;
  public static String ShowInDiagramAction_Title;
  public static String ImpactAnalysisDialog_Title;
  public static String ImpactAnalysisDialog_Message;
  public static String LocateInCapellaExplorerAction_SelectedElementNotVisible_0;
  public static String LocateInCapellaExplorerAction_SelectedElementNotVisible_4;
  public static String LocateInCapellaExplorerAction_SelectedElementNotVisible_5;
  public static String LocateInCapellaExplorerAction_SelectedElementNotVisible_Message;
  public static String LocateInCapellaExplorerAction_SelectedElementNotVisible_Title;
  public static String LocateInCapellaExplorerAction_GoToReferencedElement;
  public static String SortContentAction_Label;
  public static String SortSelectionAction_Label;

  public static String ShowInDiagramAction_UnknownElement_Title;
  public static String ShowInDiagramAction_UnknownElement_Message;
  public static String ShowInDiagramAction_HiddenElement_Message;
  public static String ShowInDiagramAction_CollapseElement_Message;
  public static String ShowInDiagramAction_FoldedElement_Message;
  public static String ShowInDiagramAction_FilteredElement_Message;

  public static String RenameResourceAction_Session_Warning_Dialog_Title;
  public static String RenameResourceAction_Session_Warning_Dialog_Message;


  public static String LocateInCommonNavigator_SelectedElementNotVisible_0;
  public static String LocateInCommonNavigator_SelectedElementNotVisible_1;
  
  public static String LocateInCommonNavigator_SelectedElementNotVisible_Title;
  public static String QuickFiltersMenu_DeselectAll_Title;
  public static String QuickFiltersMenu_SelectAll_Title;
  public static String QuickFiltersMenu_SelectDefault_Title;
  public static String QuickFiltersMenu_Title;
  
  public static String OpenRelatedDiagram_Message;
  public static String OpenActionLabel;
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
