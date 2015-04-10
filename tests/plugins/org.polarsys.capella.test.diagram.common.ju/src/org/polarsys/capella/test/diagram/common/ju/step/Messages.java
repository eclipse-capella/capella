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
package org.polarsys.capella.test.diagram.common.ju.step;

import org.eclipse.osgi.util.NLS;

/**
 * Basic Test class messages. 
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.test.diagram.common.ju.steps.messages"; //$NON-NLS-1$
  
  public static String nullSession;
  public static String nullSemanticObject;
  public static String nullDiagram;
  public static String failToRefreshDiagram;
  public static String failToCreateDriagram;
  public static String nullRepresentationDesc;
  public static String cannotGetDeltaOnDiagramElement;
  public static String visiblePortLabelError;

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
