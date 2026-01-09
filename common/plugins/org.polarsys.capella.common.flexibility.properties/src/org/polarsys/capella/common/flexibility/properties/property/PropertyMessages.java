/*******************************************************************************
 * Copyright (c) 2026 THALES GLOBAL SERVICES and others.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.flexibility.properties.property;

import org.eclipse.osgi.util.NLS;

/**
 */
public class PropertyMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.flexibility.properties.property.messages"; //$NON-NLS-1$

  public static String StringPropertyPreference_1;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, PropertyMessages.class);
  }

  private PropertyMessages() {
    // nothing here
  }
}
