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
package org.polarsys.capella.test.diagram.misc.ju;

import org.eclipse.osgi.util.NLS;

/**
 * Basic Test class messages. 
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.test.diagram.misc.ju.messages"; //$NON-NLS-1$
  
  public static String nullDiagram;
  public static String sessionDirtyAfterDiagramOpening;
  public static String failToRefreshDiagram;

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
