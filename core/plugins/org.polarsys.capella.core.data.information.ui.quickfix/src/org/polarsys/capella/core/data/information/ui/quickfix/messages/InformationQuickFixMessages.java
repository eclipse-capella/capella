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
package org.polarsys.capella.core.data.information.ui.quickfix.messages;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.information.ui.quickfix.InformationQuickFixActivator;

/**
 * I18n support for this plugin
 */
public class InformationQuickFixMessages extends NLS {
  private static final String BUNDLE_NAME = InformationQuickFixActivator.getDefault().getPluginId() + ".messages.messages"; //$NON-NLS-1$

  public static String cycle_details_dialog_title;
  public static String cycle_details_dialog_message;

  public static String cycle_details_dialog_combo_lbl;
  public static String cycle_details_dialog_combo_all_cycles;
  public static String cycle_details_dialog_combo_cycle_prefix;
  
  public static String cO1218_exception_message;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, InformationQuickFixMessages.class);
  }

  private InformationQuickFixMessages() {
    // Do nothing.
  }
}
