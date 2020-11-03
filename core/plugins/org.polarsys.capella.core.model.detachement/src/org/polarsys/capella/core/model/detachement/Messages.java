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
package org.polarsys.capella.core.model.detachement;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages {
  
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.model.detachement.messages"; //$NON-NLS-1$
  
  public static String CAPELLA_LIBRARIES;
  public static String CAPELLA_LIBRARIES_DESC;
  public static String SELECT_ALL_LABEL;
  public static String DESELECT_ALL_LABEL;
  
  static {
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }
  
  private Messages() {
  }
}
