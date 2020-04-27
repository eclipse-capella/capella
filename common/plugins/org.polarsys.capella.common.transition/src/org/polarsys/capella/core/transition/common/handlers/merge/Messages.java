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
package org.polarsys.capella.core.transition.common.handlers.merge;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.transition.common.handlers.merge.messages"; //$NON-NLS-1$
  public static String DefaultFocusCategoryFilter;
  public static String DefaultFocusCategoryFilter_Description;
  public static String DefaultMergeHandler_CategoryBusiness_Description;
  public static String DefaultMergeHandler_CategoryBusiness_Name;
  public static String DefaultMergeHandler_CategorySemantic_Description;
  public static String DefaultMergeHandler_CategorySemantic_Name;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
