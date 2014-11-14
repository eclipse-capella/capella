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
package org.polarsys.capella.core.refinement.preferences.ui.messages;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class RefinementPrefUIMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.refinement.preferences.ui.messages.messages"; //$NON-NLS-1$
  
  // Main preference page
  
  public static String RefinementPreferencePage_Title;
  public static String RefinementPreferencePage_Description;

  // diagram creation/opening policy preferences
  
  public static String RefinedDiagramManagementPreferencePage_Group_Title;
  public static String RefinedDiagramManagementPreferencePage_Creation_Title;
  public static String RefinedDiagramManagementPreferencePage_Opening_Title;
  
  // validation during merge policy preferences
  
  public static String RefinedDiagramManagementPreferencePage_GroupMerge_Title;
  public static String RefinedDiagramManagementPreferencePage_GroupMerge_Tooltip;
  public static String RefinedDiagramManagementPreferencePage_Merge_AllowPreValidation_Label;
  public static String RefinedDiagramManagementPreferencePage_Merge_StopOnErrorDuringPreValidation_Label;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, RefinementPrefUIMessages.class);
  }

  private RefinementPrefUIMessages() {
    // Private constructor.
  }
}
