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
package org.polarsys.capella.core.ui.properties.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.properties.wizards.messages"; //$NON-NLS-1$

  public static String CustomWizardHandler_Command_Title;
  public static String CapellaElement_SectionLabel;

  // Help on wizard
  
  public static String editCapellaCustomPropertyWizardPage_Description;
  public static String editCapellaCustomPropertyWizardPage_MainDescription;
  public static String editCapellaCustomPropertyWizardPage_MainDescriptionKO;
  public static String editCapellaCustomPropertyWizardPage_RLLinkName;
  
  public static String editCapellaCustomPropertyWizard_WindowTitle;
  
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
