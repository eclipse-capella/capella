/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.wrapper;

import org.eclipse.osgi.util.NLS;

/**
 * Tool wrapper and test messages
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.test.diagram.common.ju.wrapper.messages"; //$NON-NLS-1$

  public static String toolWrapperArgumentErr;
  public static String toolWrapperArgumentValueErr;
  public static String toolWrapperArgumentValueFailedErr;
  public static String toolWrapperNotAvailable;

  public static String toolDoesNotExist;

  public static String trackingToolsChangeTestName;

  public static String argumentNotInTheScopeOfWrapper;

  public static String duplicatedToolNameDetected;

  public static String newToolDetectedInDiagram;

  public static String removedToolDetectedInDiagram;

  public static String renamedToolDetectedInDiagram;

  public static String toolCanBeExecuted;

  public static String reconnectEdgeSourceFailed;
  public static String reconnectEdgeTargetFailed;

  public static String toolPreconditionFailed;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Constructor.
   */
  private Messages() {
    // Do nothing.
  }
}
