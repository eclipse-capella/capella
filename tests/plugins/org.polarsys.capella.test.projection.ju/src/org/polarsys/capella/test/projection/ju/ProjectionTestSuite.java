/*******************************************************************************
 * Copyright (c) 2017, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.projection.ju;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.test.framework.api.BasicTestArtefact;
import org.polarsys.capella.test.framework.api.BasicTestSuite;

import junit.framework.Test;

public class ProjectionTestSuite extends BasicTestSuite {

  @Override
  protected List<BasicTestArtefact> getTests() {
    return Arrays.<BasicTestArtefact> asList(new GenerateInterfacesTest("test1"), //$NON-NLS-1$
        new GenerateInterfacesTest("test2.1"), //$NON-NLS-1$
        new GenerateInterfacesTest("test2.2"), //$NON-NLS-1$
        new GenerateInterfacesTest("test3"), //$NON-NLS-1$
        new GenerateInterfacesTest("test4.1"), //$NON-NLS-1$
        new GenerateInterfacesTest("test4.2"), //$NON-NLS-1$
        new GenerateInterfacesTest("test5.1"), //$NON-NLS-1$
        new GenerateInterfacesTest("test5.2"), //$NON-NLS-1$
        new GenerateInterfacesTest("test5.3"), //$NON-NLS-1$
        new GenerateInterfacesTest("test5.4"), //$NON-NLS-1$
        new GenerateInterfacesTest("test6.1"), //$NON-NLS-1$
        new GenerateInterfacesTest("test6.2"), //$NON-NLS-1$
        new GenerateInterfacesTest("test7"), //$NON-NLS-1$
        new GenerateInterfacesTest("test8"), //$NON-NLS-1$
        new GenerateInterfacesTest("test9.1"), //$NON-NLS-1$
        new GenerateInterfacesTest("test9.2"), //$NON-NLS-1$
        new GenerateInterfacesTest("test9.3"), //$NON-NLS-1$
        new GenerateInterfacesTest("test9.4"), //$NON-NLS-1$
        new Rule_DWF_I_23(),
        new FC2FSTest()
    );
  }

  /**
   * Returns the suite. This is required to unary launch this test.
   */
  public static Test suite() {
    return new ProjectionTestSuite();
  }

  @Override
  public List<String> getRequiredTestModels() {
    return null;
  }

}
