/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.validation.physicalPath;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.cs.validation.physicalPath.messages"; //$NON-NLS-1$
  public static String MDCHK_PhysicalPath_InvolvedLinks_ContainsACycle;
  public static String MDCHK_PhysicalPath_InvolvedLinks_IsEmpty;
  public static String MDCHK_PhysicalPath_InvolvedLinks_NotWellFormed;
  public static String MDCHK_PhysicalPath_InvolvedLinks_NoSource;
  public static String MDCHK_PhysicalPath_InvolvedLinks_InvolvementInvalid;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    //Nothing here
  }
}
