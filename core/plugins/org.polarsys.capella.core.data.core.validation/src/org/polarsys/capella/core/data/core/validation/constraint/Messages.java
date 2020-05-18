/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.constraint;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.core.validation.constraint.messages"; //$NON-NLS-1$
  public static String I_38_ReferenceConstraints_detail;
  public static String I_38_ReferenceConstraints_title;
  public static String I_38_ReferenceConstraintsListener_detail;
  public static String I_38_ReferenceConstraintsListener_ruleID;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
