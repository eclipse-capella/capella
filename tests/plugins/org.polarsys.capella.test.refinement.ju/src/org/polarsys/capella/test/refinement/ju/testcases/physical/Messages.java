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
package org.polarsys.capella.test.refinement.ju.testcases.physical;

import org.eclipse.osgi.util.NLS;

/**
 * Messages externalization for this package
 */
public class Messages extends NLS {

  private static final String BUNDLE_NAME = "org.polarsys.capella.test.refinement.ju.testcases.physical.messages"; //$NON-NLS-1$

  // Physical TCs
  public static String PhysicalTC_PhysicalArchitecture;
  public static String PhysicalTC_PC1;
  public static String PhysicalTC_PC11_Part;
  public static String PhysicalTC_PC12_Part;
  public static String PhysicalTC_PC2;
  public static String PhysicalTC_EPBSArchitecture;

  public static String PhysicalTC_LA_2_PA_Name;
  public static String PhysicalTC_LA_2_PA_Desc;
  public static String PhysicalTC_LA_2_PA_Src;
  public static String PhysicalTC_LA_2_PA_Ref;
  public static String PhysicalTC_PA_2_PC1_Name;
  public static String PhysicalTC_PA_2_PC1_Desc;
  public static String PhysicalTC_PA_2_PC1_Src;
  public static String PhysicalTC_PA_2_PC1_Ref;
  public static String PhysicalTC_PC1_2_PC11_Name;
  public static String PhysicalTC_PC1_2_PC11_Desc;
  public static String PhysicalTC_PC1_2_PC11_Src;
  public static String PhysicalTC_PC1_2_PC11_Ref;
  public static String PhysicalTC_PC1_2_PC12_Name;
  public static String PhysicalTC_PC1_2_PC12_Desc;
  public static String PhysicalTC_PC1_2_PC12_Src;
  public static String PhysicalTC_PC1_2_PC12_Ref;
  public static String PhysicalTC_PA_2_PC2_Name;
  public static String PhysicalTC_PA_2_PC2_Desc;
  public static String PhysicalTC_PA_2_PC2_Src;
  public static String PhysicalTC_PA_2_PC2_Ref;

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
