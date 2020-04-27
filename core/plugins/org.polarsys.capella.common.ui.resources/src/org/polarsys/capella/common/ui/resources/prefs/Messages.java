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
package org.polarsys.capella.common.ui.resources.prefs;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.ui.resources.prefs.messages"; //$NON-NLS-1$

  public static String ModelPreferencePage_Title;
  public static String ModelPreferencePage_Description;

  public static String ReuseOfComponentsPreferencePage_Group_Title;
  public static String ReuseOfComponentsPreferencePage_Allowed_Title;

  public static String InheritancePreferencePage_Group_Title;
  public static String MultipleInheritancePreferencePage_Allowed_Title;
  public static String ComponentInheritancePreferencePage_Allowed_Title;
  public static String ComponentNonActorInheritancePreferencePage_Allowed_Title;

  public static String DeploymentPreferencePage_Group_Title;
  public static String DeploymentPreferencePage_Allowed_Title;

  public static String DataPreferencePage_Group_Title;
  public static String DataPreferencePage_PrimitiveSynchroAllowed_Title;

  public static String SyncPreferencePage_Group_Title;
  public static String SyncPreferencePage_SyncComponentPort2FunctionPortAllowed_Title;
  public static String SyncPreferencePage_SyncPhysicalPort2FunctionPortOnPhysicalLinkAllowed_Title;
  public static String SyncPreferencePage_SyncPhysicalPort2FunctionPortOnPhysicalPathAllowed_Title;

  public static String ModeAndState_Group_Title;
  public static String ModeAndState_MixedHierarchy_Title;

  public static String PhysicalComponenentProperties_Group_Title;
  public static String PhysicalComponenentNatureChange_Title;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
