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

import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Transition of a LogicalComponent should lead to a PhysicalComponent with nature BEHAVIOR
 */
public class LCPCTransition_02 extends TopDownTransitionTestCase {

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    final String id_lc1 = "e65e71fd-5d40-41e5-910a-303fd55a0e51";
    performLCtoPCTransition(Arrays.asList(getObject(id_lc1)));
    LogicalComponent component = getObject(id_lc1);
    PhysicalArchitecture pa = SystemEngineeringExt
        .getOwnedPhysicalArchitecture(SystemEngineeringExt.getSystemEngineering(component));
    PhysicalComponent rootPC = (PhysicalComponent) pa.getSystem();
    assertEquals(1, rootPC.getOwnedPhysicalComponents().size());
    assertEquals(PhysicalComponentNature.BEHAVIOR, rootPC.getOwnedPhysicalComponents().get(0).getNature());
  }

}
