/*******************************************************************************
 * Copyright (c) 2017, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.fa.ui.quickfix.generator;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
  private static final String BUNDLE_NAME = "org.polarsys.capella.core.data.fa.ui.quickfix.generator.messages"; //$NON-NLS-1$
  public static String DWF_DC_43_Resolutions_0;
  public static String DWF_DF_16_Resolutions_1;
  public static String DWF_DF_18_Resolutions_1;
  public static String DWF_DF_19_Resolutions_1;
  public static String DWF_DF_19_Resolutions_2;
  public static String DWF_DF_20_Resolutions_1;
  public static String DWF_DF_20_Resolutions_2;
  public static String GenerateInterfacesResolutionGenerator_allocateEIOnInterface;
  public static String GenerateInterfacesResolutionGenerator_allocateOnSelectedFECE;
  public static String GenerateInterfacesResolutionGenerator_deallocateEIFromInterface;
  public static String GenerateInterfacesResolutionGenerator_deallocateFromFECE;
  static {
    // initialize resource bundle
    NLS.initializeMessages(BUNDLE_NAME, Messages.class);
  }

  private Messages() {
  }
}
