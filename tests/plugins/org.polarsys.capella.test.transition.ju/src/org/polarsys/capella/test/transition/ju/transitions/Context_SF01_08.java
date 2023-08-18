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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * <pre>
 * Perform a Functional Transition on Root Logical Function
 * 
 * 
 * Expected Result:\
 * At EPBS Architecture layer, there is no DataPkg and InterfacePkg
 * </pre>
 */
public class Context_SF01_08 extends TopDownTransitionTestCase {

  private String id_RootLF = "b98fba71-a520-40e3-b831-7c37dd48e9a4";
  private String id_EPBSArchitecture = "ee1c572d-0451-4c10-be02-8379640eae5c";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_RootLF)));
    EPBSArchitecture epbsArch = (EPBSArchitecture) getObject(id_EPBSArchitecture);
    assertTrue("- DataPkg, InterfacePkg should not be created at EPBS layer during a LC-PC transition",
        epbsArch.getOwnedDataPkg() == null);
    assertTrue("- DataPkg, InterfacePkg should not be created at EPBS layer during a LC-PC transition",
        epbsArch.getOwnedInterfacePkg() == null);
  }
}