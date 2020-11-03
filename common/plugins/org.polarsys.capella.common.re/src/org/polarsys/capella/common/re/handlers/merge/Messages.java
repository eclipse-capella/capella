/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.re.handlers.merge;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.common.re.handlers.merge.messages"; //$NON-NLS-1$

  public static String AvoidReAttributeCategoryFilter;
  public static String AvoidReAttributeCategoryFilter_Description;
  public static String AvoidMergeUnmodifiableCategoryFilter;
  public static String AvoidMergeUnmodifiableCategoryFilter_Description;
  public static String AvoidUnsynchronizedFeatureCategoryFilter;
  public static String AvoidUnsynchronizedFeatureCategoryFilter_Description;
  
  
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
