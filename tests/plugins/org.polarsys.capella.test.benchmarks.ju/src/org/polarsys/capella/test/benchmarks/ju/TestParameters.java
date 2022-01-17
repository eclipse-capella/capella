/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.benchmarks.ju;

import org.eclipse.core.runtime.IConfigurationElement;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;

public class TestParameters implements ITestParameters {
  private static ITestParameters instance;
  public final static String TEST_PARAMETER_EXT_POINT = "testparams";

  private TestParameters() {
    // Private constructor
  }

  public static ITestParameters getInstance() {
    if (instance == null) {
      for (IConfigurationElement configElement : ExtensionPointHelper
          .getConfigurationElements(FrameworkUtil.getBundle(TestParameters.class).getSymbolicName(), TEST_PARAMETER_EXT_POINT)) {
        instance = (ITestParameters) ExtensionPointHelper.createInstance(configElement, ExtensionPointHelper.ATT_CLASS);
      }

      if (instance == null)
        instance = new TestParameters();
    }
    return instance;
  }

  /**
   * 
   * If there's no contribution, by default the IFE will be the test model
   */
  @Override
  public String getTestModelName() {
    return "In-Flight Entertainment System";
  }
}
