/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.testcases.bugfixes;

import org.eclipse.osgi.util.NLS;

/**
 * Messages externalization for this package
 *
 */
public class HoldingResourceTestMessages extends NLS {

  private static final String BUNDLE_NAME = "org.polarsys.capella.test.refinement.ju.testcases.bugfixes.messages"; //$NON-NLS-1$

  // Holding resource test case
  public static String HoldingResourceTC_Name;
  public static String HoldingResourceTC_Desc;
  public static String HoldingResourceTC_ToRefine;
  public static String HoldingResource_LogicalArchitecture;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, HoldingResourceTestMessages.class);
  }

  /**
   * Constructor.
   */
  private HoldingResourceTestMessages() {
    // Do nothing.
  }
}
