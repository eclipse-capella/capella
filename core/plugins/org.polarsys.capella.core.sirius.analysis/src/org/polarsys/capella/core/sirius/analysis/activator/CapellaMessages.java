/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.activator;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class CapellaMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.sirius.analysis.activator.messages"; //$NON-NLS-1$
  public static String ArrangeBorderNodesAction_actionText;
  public static String ArrangeBorderNodesAction_commandLabel;
  public static String ArrangeBorderNodesAction_toolbarActionText;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, CapellaMessages.class);
  }

  private CapellaMessages() {
  }
}
