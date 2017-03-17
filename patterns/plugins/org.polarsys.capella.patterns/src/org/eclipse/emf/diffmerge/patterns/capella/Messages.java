/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.eclipse.emf.diffmerge.patterns.capella;

import org.eclipse.osgi.util.NLS;

/**
 * Utility class for the externalization mechanism
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.eclipse.emf.diffmerge.patterns.capella.messages"; //$NON-NLS-1$
  public static String Checker_Pattern;
  public static String CapellaDeleteOperationProvider_DeleteFailed;
  public static String Checker_NoCatalog;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Nothing
  }
}
