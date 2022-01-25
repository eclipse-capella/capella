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
package org.polarsys.capella.core.validation.ui.ide.messages;

import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.core.validation.ui.ide.PluginActivator;

/**
 * I18n support for this plugin
 */
public class QuickfixMessages extends NLS {
  
  public static String eobjectnavigator_dialog_title;
  public static String eobjectnavigator_dialog_message;
  public static String eobjectnavigator_dialog_combo_lbl;
  public static String eobjectnavigator_dialog_combo_all_elements;
  public static String eobjectnavigator_dialog_combo_element_prefix;
  
  public static String eobjectnavigator_goToReferencedElement;
  public static String eobjectnavigator_selectInSemanticBrowser;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(PluginActivator.getDefault().getBundle().getSymbolicName() + ".messages.messages", QuickfixMessages.class); //$NON-NLS-1$
  }

  private QuickfixMessages() {
    // Do nothing.
  }
}
