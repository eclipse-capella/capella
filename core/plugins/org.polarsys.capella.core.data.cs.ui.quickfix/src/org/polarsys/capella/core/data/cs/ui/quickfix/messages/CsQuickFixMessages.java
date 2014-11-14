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
