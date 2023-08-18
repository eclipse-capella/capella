/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.pa.PhysicalFunction;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Create a LC and a Function
 * Do a physical transition of LC
 * Create a child on physical function and change allocation to this new function
 * Redo a transition, parent function must not be allocated by default
 */
public class NonLeafAllocation extends TopDownTransitionTestCase {
  
  public static final String LC_1 = "f54d830e-6d1f-4ef9-b1c7-990c75d4c255"; //$NON-NLS-1$
  public static final String PLOGICALFUNCTION_1 = "23a24c95-3799-43fe-b543-3d2f7db2eb84"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("NonLeafAllocation");
  }

  @Override
  public void performTest() throws Exception {
    performLCtoPCTransition(Arrays.asList(getObject(LC_1)));
    mustBeTransitioned(LC_1);
    PhysicalFunction function = getObject(PLOGICALFUNCTION_1);
    assertTrue(function.getComponentFunctionalAllocations().size() == 0);
  }

}
