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
 * - Create LC2 and a function allocated to LC2
 * - Do transition on Physical layer
 * - Create a new PC3 and allocate the function to it and remove TransfoLink on allocation if exists
 * - Do a transition, allocation shall not be added on LC2 in addition of the existing LC3 one
 */
public class TwiceAllocation extends TopDownTransitionTestCase {

  public static final String LC_1 = "fc400b0d-1013-4080-9abc-3a1409544db3"; //$NON-NLS-1$
  public static final String LC_2 = "b26d6b94-8fb4-48fa-a587-d7bf655ab89d"; //$NON-NLS-1$
  public static final String PHYSICALFUNCTION_2 = "7edbd353-c951-4f3b-9a37-58f7ed57a2e1"; //$NON-NLS-1$
  public static final String PC_3 = "972f1e0f-47e9-4aac-84cc-c08552d04a76"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("TwiceAllocation");
  }

  @Override
  public void performTest() throws Exception {
    performLCtoPCTransition(getObjects(LC_1, LC_2));
    PhysicalFunction function = getObject(PHYSICALFUNCTION_2);
    assertTrue(function.getComponentFunctionalAllocations().size() == 1);
    assertTrue(function.getAllocatingPhysicalComponents().contains(getObject(PC_3)));
  }

}