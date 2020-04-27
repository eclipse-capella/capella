/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers;

import org.eclipse.osgi.util.NLS;

/**
 * Messages externalization for this package
 */
public class HelperMessages extends NLS {

  private static final String BUNDLE_NAME = "org.polarsys.capella.test.framework.helpers.messages"; //$NON-NLS-1$

  public static String invalidInteger;
  public static String wrongEObjectType;
  public static String wrongEObjectTypeWithInheritance;

  //
  // FilterOnDiagramHelper Messages
  //
  public static String filterNotFound;
  public static String diagramNotContainedInSession;

  static {
    // Initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, HelperMessages.class);
  }

  /**
   * Constructor.
   */
  private HelperMessages() {
    // Do nothing.
  }
}
