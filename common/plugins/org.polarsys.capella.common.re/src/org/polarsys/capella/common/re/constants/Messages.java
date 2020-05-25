/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.constants;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.re.constants.messages"; //$NON-NLS-1$
  public static String ReLauncher_Title;
  public static String Update_REC_from_RPL_only_one;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
