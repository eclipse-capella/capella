/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.common.ju.step.filters;

import org.eclipse.osgi.util.NLS;

/**
 * Messages for {@link AbstractFilterTrackChangesTest}
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.test.diagram.common.ju.step.filters.messages"; //$NON-NLS-1$
  public static String duplicatedFilterIDDetected;
  public static String newFilterCreationDetected;
  public static String filterRemovalDetected;
  public static String renamedFiltersFound;
  public static String filtersNotActivated;
  public static String expectedAmountNotFound;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // Do Nothing
  }
}
