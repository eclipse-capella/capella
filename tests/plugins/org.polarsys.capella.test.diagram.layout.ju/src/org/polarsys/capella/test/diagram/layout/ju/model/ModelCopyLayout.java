/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.diagram.layout.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestCase;

public abstract class ModelCopyLayout extends BasicTestCase {

  public static String LAYOUT_A_SOURCE_FUNCTIONS = "_wTk3cMfEEeifMqDVELmDAQ";
  public static String LAYOUT_A_TARGET_FUNCTIONS = "_TYwG4MfEEeifMqDVELmDAQ";

  public static String LAYOUT_B_SOURCE_REC = "_Fl4-gMfFEeifMqDVELmDAQ";
  public static String LAYOUT_B_TARGET_RPL = "_5YFdgMfHEeifMqDVELmDAQ";

  public static String RPL = "edacf6c3-4933-4bf9-94f0-07469f691275";
  public static String LOGICAL_ARCHITECTURE = "e4636e76-02d1-4ee6-a13d-b8965d5ec476";
  public static String PHYSICAL_ARCHITECTURE = "6a12ca78-017e-4fbd-ab16-977e17243619";

  public static String LAYOUT_C_SOURCE_TRANSITION = "_GPa8UMfIEeifMqDVELmDAQ";
  public static String LAYOUT_C_TARGET_TRANSITION = "_TtsfUMfIEeifMqDVELmDAQ";

  public static String LAYOUT_D_SOURCE_COMPONENT_EXCHANGES = "_lObOUMfMEeiylrB6GzuVdQ";
  public static String LAYOUT_D_TARGET_COMPONENT_EXCHANGES = "_5s0lIMfMEeiylrB6GzuVdQ";

  public static String LAYOUT_E_SOURCE_FUNCTIONAL_CHAINS = "_8ComIMfxEeictfpJiIBryA";
  public static String LAYOUT_E_TARGET_FUNCTIONAL_CHAINS = "_A4SGcMfyEeictfpJiIBryA";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("modelCopyLayout");
  }

}
