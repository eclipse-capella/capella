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
package org.polarsys.capella.core.compare;

import org.eclipse.osgi.util.NLS;


/**
 * Message management.
 */
@SuppressWarnings("javadoc")
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.compare.messages"; //$NON-NLS-1$
  public static String CapellaComparisonFactory_Label;
  public static String CapellaScope_DifferentVersion;
  public static String CapellaScope_DifferentVersionInterruption;
  public static String CapellaScope_PermissionCommandName;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }
  
  /**
   * Constructor
   */
  private Messages() {
    // Nothing needed
  }
}
