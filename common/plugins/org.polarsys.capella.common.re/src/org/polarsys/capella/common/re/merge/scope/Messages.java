/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.merge.scope;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.re.merge.scope.messages"; //$NON-NLS-1$
  public static String ReSourceScope_candidateModel;
  public static String ReSourceScope_REC;
  public static String ReSourceScope_selectedRPL;
  public static String ReSourceScope_selection;
  public static String ReTargetScope_REC;
  public static String ReTargetScope_resultingModel;
  public static String ReTargetScope_selectedRPL;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
