/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.testcases.withfragments;

import org.eclipse.osgi.util.NLS;

/**
 * Messages externalization for this package
 */
public class Messages extends NLS {

  private static final String BUNDLE_NAME = "org.polarsys.capella.test.refinement.ju.testcases.withfragments.messages"; //$NON-NLS-1$

  // Simple TCs
  public static String SimpleTC_LogicalArchitecture;
  public static String SimpleTC_LC1;
  public static String SimpleTC_LC2;
  public static String SimpleTC_LC3;
  public static String SimpleTC_PhysicalArchitecture;

  public static String SimpleTC_WithInteractionFragmentTC0_Name;
  public static String SimpleTC_WithInteractionFragmentTC0_Desc;
  public static String SimpleTC_WithInteractionFragmentTC0_Src;
  public static String SimpleTC_WithInteractionFragmentTC0_Ref;
  public static String SimpleTC_WithInteractionFragmentTC1_Name;
  public static String SimpleTC_WithInteractionFragmentTC1_Desc;
  public static String SimpleTC_WithInteractionFragmentTC1_Src;
  public static String SimpleTC_WithInteractionFragmentTC1_Ref;
  public static String SimpleTC_WithInteractionFragmentTC2_Name;
  public static String SimpleTC_WithInteractionFragmentTC2_Desc;
  public static String SimpleTC_WithInteractionFragmentTC2_Src;
  public static String SimpleTC_WithInteractionFragmentTC2_Ref;
  public static String SimpleTC_WithInteractionState_Name;
  public static String SimpleTC_WithInteractionState_Desc;
  public static String SimpleTC_WithInteractionState_Src;
  public static String SimpleTC_WithInteractionState_Ref;
  public static String SimpleTC_WithInteractionUseTC0_Name;
  public static String SimpleTC_WithInteractionUseTC0_Desc;
  public static String SimpleTC_WithInteractionUseTC0_Src;
  public static String SimpleTC_WithInteractionUseTC0_Ref;
  public static String SimpleTC_WithInteractionUseTC1_Name;
  public static String SimpleTC_WithInteractionUseTC1_Desc;
  public static String SimpleTC_WithInteractionUseTC1_Src;
  public static String SimpleTC_WithInteractionUseTC1LC1_Ref;
  public static String SimpleTC_WithInteractionUseTC1LC2_Ref;
  public static String SimpleTC_WithInteractionUseTC1LC3_Ref;

  // regression TC
  public static String regression_TC_LogicalArchitecture;

  public static String regression_TC_Name;
  public static String regression_TC_Desc;
  public static String regression_TC_Src;
  public static String regression_TC_Ref;

  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  /**
   * Constructor.
   */
  private Messages() {
    // Do nothing.
  }
}
