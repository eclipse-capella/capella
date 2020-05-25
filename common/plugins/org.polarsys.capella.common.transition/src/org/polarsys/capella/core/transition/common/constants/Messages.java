/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.constants;

import org.eclipse.osgi.util.NLS;

/**
 *
 */
public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.transition.common.constants.messages"; //$NON-NLS-1$

  public static String Activity_ManagementActivity;
  public static String Activity_ComputingDifferenceActivity;
  public static String Activity_FilteringDifferenceActivity;
  public static String Activity_MergingDifferenceActivity;
  public static String Activity_Transition;
  public static String Activity_Transformation;
  public static String TransitionedElement_Suffix;
  public static String TraceabilityHandler;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
