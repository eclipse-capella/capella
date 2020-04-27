/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.layout.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestCase;

public abstract class ModelCopyLayout extends BasicTestCase {

  public static String LAYOUT_A_SOURCE_FUNCTIONS = "_wTuphcfEEeifMqDVELmDAQ";
  public static String LAYOUT_A_TARGET_FUNCTIONS = "_TZDB0cfEEeifMqDVELmDAQ";

  public static String LAYOUT_B_SOURCE_REC = "_FmCvgMfFEeifMqDVELmDAQ";
  public static String LAYOUT_B_TARGET_RPL = "_5YFdhcfHEeifMqDVELmDAQ";

  public static String RPL = "edacf6c3-4933-4bf9-94f0-07469f691275";
  public static String LOGICAL_ARCHITECTURE = "e4636e76-02d1-4ee6-a13d-b8965d5ec476";
  public static String PHYSICAL_ARCHITECTURE = "6a12ca78-017e-4fbd-ab16-977e17243619";

  public static String LAYOUT_C_SOURCE_TRANSITION = "_GPkGRcfIEeifMqDVELmDAQ";
  public static String LAYOUT_C_TARGET_TRANSITION = "_TuJLQ8fIEeifMqDVELmDAQ";

  public static String LAYOUT_D_SOURCE_COMPONENT_EXCHANGES = "_lPiooMfMEeiylrB6GzuVdQ";
  public static String LAYOUT_D_TARGET_COMPONENT_EXCHANGES = "_5s-WccfMEeiylrB6GzuVdQ";

  public static String LAYOUT_E_SOURCE_FUNCTIONAL_CHAINS = "_8C8vMMfxEeictfpJiIBryA";
  public static String LAYOUT_E_TARGET_FUNCTIONAL_CHAINS = "_A4Vw0MfyEeictfpJiIBryA";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("modelCopyLayout");
  }

}
