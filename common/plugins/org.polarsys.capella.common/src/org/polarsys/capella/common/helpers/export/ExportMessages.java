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
package org.polarsys.capella.common.helpers.export;

import org.eclipse.osgi.util.NLS;

/**
 * I18n support for this package
 */
public class ExportMessages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.helpers.export.messages"; //$NON-NLS-1$

  public static String errNullExporterProvider;
  
  public static String csvDesc;
  public static String txtDesc;
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, ExportMessages.class);
  }

  private ExportMessages() {
    // Do nothing.
  }
}
