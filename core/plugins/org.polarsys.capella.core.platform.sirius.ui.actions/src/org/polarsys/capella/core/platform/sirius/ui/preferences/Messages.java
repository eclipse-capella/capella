/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.preferences;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.platform.sirius.ui.preferences.messages"; //$NON-NLS-1$
  public static String DeletePreferencePage_ConfirmDeleteTitle;
  public static String DeletePreferencePage_DeleteChoice;
  public static String DeletePreferencePage_DeletePartsChoiceTitle;
  public static String DeletePreferencePage_Description;
  public static String DeletePreferencePage_Title;
  public static String CapellaPreferencePage_AirdFragmentFileExtension_Title;
  public static String CapellaPreferencePage_Description;
  public static String CapellaPreferencePage_DetectionVersion_Title;
  public static String CapellaPreferencePage_Title;
  public static String WizardPreferencePage_Description;
  public static String WizardPreferencePage_Title;
  public static String ModelValidationPreferencePage_Description;
  public static String ModelValidationPreferencePage_DeletePreviousResults_Title;
  public static String DeletePreferencePage_ProtectedElements_Title;

  public static String DeletePreferencePage_MultipartGroup_Title;
  public static String DeletePreferencePage_MultipartGroup_Description;

  public static String TitleBlockPreferencePage_Title;
  public static String TitleBlockPreferencePage_Description;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Static initialization.
  }
}
