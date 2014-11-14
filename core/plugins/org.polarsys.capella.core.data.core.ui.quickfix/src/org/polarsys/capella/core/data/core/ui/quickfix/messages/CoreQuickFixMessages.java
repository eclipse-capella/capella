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
package org.polarsys.capella.core.data.core.ui.quickfix.messages;

import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.core.data.core.ui.quickfix.CoreQuickFixActivator;

/**
 * I18n support for this plugin
 */
public class CoreQuickFixMessages extends NLS {
  private static final String BUNDLE_NAME = CoreQuickFixActivator.getDefault().getPluginId() + ".messages.messages"; //$NON-NLS-1$

  public static String cycle_details_dialog_title;
  public static String cycle_details_dialog_message;
  public static String cycle_details_dialog_combo_lbl;
  public static String cycle_details_dialog_combo_all_cycles;
  public static String cycle_details_dialog_combo_cycle_prefix;

  public static String goToReferencedElement;
  public static String selectInSemanticBrowser;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, CoreQuickFixMessages.class);
  }

  private CoreQuickFixMessages() {
    // Do nothing.
  }
}
