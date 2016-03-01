/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.framework.api;

import org.eclipse.osgi.util.NLS;

/**
 * Basic Test class messages.
 */
public class CommonTestMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.test.framework.api.commontestmessages"; //$NON-NLS-1$

  // AbstractCapellaTest
  public static String timeToRunTest;
  public static String timeToRunTestFail;
  public static String invalidExpectedTime;

  // Common for test
  public static String nullSession;
  public static String nullSemanticObject;
  public static String nullDiagram;

  public static String objectRepresentationStillAvailableOnDiagram;
  public static String objectRepresentationNotAvailableOnDiagram;
  public static String wrongMapping;

  public static String noDescriptionAvailable;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, CommonTestMessages.class);
  }

  /**
   * Constructor.
   */
  private CommonTestMessages() {
    // Do nothing.
  }
}
