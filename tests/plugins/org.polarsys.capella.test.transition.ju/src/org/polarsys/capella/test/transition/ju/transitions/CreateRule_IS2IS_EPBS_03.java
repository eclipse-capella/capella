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

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.model.CreateRule_Scenario_IS2IS_EPBS;

public class CreateRule_IS2IS_EPBS_03 extends CreateRule_Scenario_IS2IS_EPBS {

  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__PCCI_ENABLED, false);

    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITYREALIZATION_1)));

    InstanceRole role2 = mustBeMonoTransitioned(IR_PC_2);
    InstanceRole role3 = mustBeMonoTransitioned(IR_PC_3);
    assertEquals(role2.getRepresentedInstance().getType(), getObject(COTSCI_COTSCI_1));
    assertEquals(role3.getRepresentedInstance().getType(), getObject(COTSCI_COTSCI_1));

    InstanceRole role4 = mustBeMonoTransitioned(IR_PC_4);
    InstanceRole role5 = mustBeMonoTransitioned(IR_PC_5);
    assertEquals(role4.getRepresentedInstance().getType(), getObject(COTSCI_COTSCI_2));
    assertEquals(role5.getRepresentedInstance().getType(), getObject(COTSCI_COTSCI_2));

    // Ensure source and target of messages are merged
    mustBeMonoTransitioned(SM23);
    mustBeMonoTransitioned(SM34);
    mustBeMonoTransitioned(SM45);
    assertEquals(getSource(SM23), getObject(COTSCI_COTSCI_1));
    assertEquals(getTarget(SM23), getObject(COTSCI_COTSCI_1));
    assertEquals(getSource(SM45), getObject(COTSCI_COTSCI_2));
    assertEquals(getTarget(SM45), getObject(COTSCI_COTSCI_2));
    assertEquals(getSource(SM34), getObject(COTSCI_COTSCI_1));
    assertEquals(getTarget(SM34), getObject(COTSCI_COTSCI_2));
  }

  protected AbstractType getSource(String sm) {
    return ((SequenceMessage) mustBeMonoTransitioned(sm)).getSendingEnd().getCoveredInstanceRoles().get(0)
        .getRepresentedInstance().getType();
  }

  protected AbstractType getTarget(String sm) {
    return ((SequenceMessage) mustBeMonoTransitioned(sm)).getReceivingEnd().getCoveredInstanceRoles().get(0)
        .getRepresentedInstance().getType();
  }
}
