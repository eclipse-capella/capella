/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.capellacommon.CapabilityRealizationInvolvement;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.model.CreateRule_Scenario_IS2IS_EPBS;

public class CreateRule_IS2IS_EPBS_04 extends CreateRule_Scenario_IS2IS_EPBS {

  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__PCCI_ENABLED, true);

    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITYREALIZATION_2)));

    // We ensure that scenario doensn't contains Role for the Actor. (as we don't transit Actor to CI)
    shouldNotBeTransitioned(IR_PA_3);

    // We ensure that owning Capability involves only two Configuration Item, not Physical Component nor Configuration
    // Item realizing Actors.
    CapabilityRealization targetCapability = (CapabilityRealization) mustBeMonoTransitioned(IS_CAPABILITYREALIZATION_2)
        .eContainer();
    assertTrue(targetCapability.getOwnedCapabilityRealizationInvolvements().size() == 2);
    for (CapabilityRealizationInvolvement e : targetCapability.getOwnedCapabilityRealizationInvolvements()) {
      assertTrue(e.getInvolved() instanceof ConfigurationItem && e.getInvolved().eContainer() != null);
    }
  }

}
