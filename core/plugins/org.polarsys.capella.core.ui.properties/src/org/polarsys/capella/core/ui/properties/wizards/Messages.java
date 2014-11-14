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
package org.polarsys.capella.core.ui.properties.wizards;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support.
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.ui.properties.wizards.messages"; //$NON-NLS-1$

  public static String CustomWizardHandler_Command_Title;
  public static String EditCapellaCustomPropertyWizardPage_Description;
  public static String CapellaElement_SectionLabel;

  // Help on wizard
  
  public static String editCapellaCustomPropertyWizardPage_MainDescription;
  public static String editCapellaCustomPropertyWizardPage_MainDescriptionKO;
  public static String editCapellaCustomPropertyWizardPage_RLLinkName;
  
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Private constructor.
  }
}
