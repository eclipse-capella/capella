/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.model;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test for Bug https://bugs.polarsys.org/show_bug.cgi?id=1918.
 */
public class AlreadyTransitionedActorTestCase extends TopDownTransitionTestCase {
  
  private static String SA__ACTORS__A1 = "798399c8-16c9-4d3b-a464-7d4a9dd1a6b7";
  private static String LA__LOGICAL_ACTORS__LA1 = "41cb15ab-cf4f-4c3b-b719-2d5ffced082a";
  private static String LA__LOGICAL_CONTEXT = "962d068a-1da9-47eb-915a-508b57180024";

  @Override
  public void performTest() throws Exception {
    SystemActorTransition();
  }

  private void SystemActorTransition() {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, Boolean.FALSE);

    ComponentPkg logicalContext = shouldExist(LA__LOGICAL_CONTEXT);

    // Before transition, assert that logical context has two features (Logical System & LA1)
    assertTrue(logicalContext.getOwnedParts().size() == 2);
   
    // Perform transition
    performActorTransition(getObjects(SA__ACTORS__A1));
   
    // Check System and Logical actors
    shouldExist(SA__ACTORS__A1);
    shouldExist(LA__LOGICAL_ACTORS__LA1);

    // After transition, assert that logical context still has two features (Logical System & LA1)
    assertTrue(logicalContext.getOwnedParts().size() == 2);
  }
 
  /*
   * (non-Javadoc)
   * @see org.polarsys.capella.test.framework.api.BasicTestArtefact#getRequiredTestModels()
   */
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("AlreadyTransitionedActor");
  }
}
