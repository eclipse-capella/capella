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
package org.polarsys.capella.core.data.cs.validation.physicalPathInvolvement;

import org.eclipse.osgi.util.NLS;

/**
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.cs.validation.physicalPathInvolvement.messages"; //$NON-NLS-1$
  public static String MDCHK_PhysicalPathInvolvement_aPhysicalPath;
  public static String MDCHK_PhysicalPathInvolvement_aPartOrPhysicalLink;
  public static String MDCHK_PhysicalPathInvolvement_PhysicalPathInvolvement;
  public static String MDCHK_PhysicalPathInvolvement_PhysicalPathReference;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
    // do nothing
  }
}
