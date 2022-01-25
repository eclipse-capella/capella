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
package org.polarsys.capella.core.data.core.ui.quickfix.messages;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.core.ui.quickfix.CoreQuickFixActivator;

/**
 * I18n support for this plugin
 */
public class CoreQuickFixMessages extends NLS {
  private static final String BUNDLE_NAME = CoreQuickFixActivator.getDefault().getBundle().getSymbolicName() + ".messages.messages"; //$NON-NLS-1$

  public static String cycle_details_dialog_title;
  public static String cycle_details_dialog_message;
  public static String cycle_details_dialog_combo_lbl;
  public static String cycle_details_dialog_combo_all_cycles;
  public static String cycle_details_dialog_combo_cycle_prefix;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, CoreQuickFixMessages.class);
  }

  private CoreQuickFixMessages() {
    // Do nothing.
  }
}
