/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.ui.quickfix.messages;

import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.core.data.cs.ui.quickfix.CsQuickFixActivator;

/**
 * I18n support for this plugin
 */
public class CsQuickFixMessages extends NLS {
  private static final String BUNDLE_NAME = CsQuickFixActivator.getDefault().getPluginId() + ".messages.messages"; //$NON-NLS-1$

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, CsQuickFixMessages.class);
  }

  private CsQuickFixMessages() {
    // Do nothing.
  }
}
