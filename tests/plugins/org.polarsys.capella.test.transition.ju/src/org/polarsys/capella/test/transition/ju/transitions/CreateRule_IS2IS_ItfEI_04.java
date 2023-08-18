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

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.model.CreateRule_Scenario_IS2IS;

public class CreateRule_IS2IS_ItfEI_04 extends  CreateRule_Scenario_IS2IS {

  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, true);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, false);

    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITY_1)));

    // InstanceRole and all operations shall refer to the original exchange item
    // As we use sequence message to find which instance role we create, operation refering the original exchange item,
    // then the instance role is this one. Otherwise, scenario will be mostly empty.
    mustBeMonoTransitioned(SharedData);
    mustBeMonoTransitioned(Operation);

    InstanceRole role = mustBeMonoTransitioned(IR_SharedData);
    assertTrue(((ExchangeItemInstance) role.getRepresentedInstance()).getAbstractType() == getObject(SharedData));

    SequenceMessage message = mustBeMonoTransitioned(SM1_SharedData);
    assertEquals(((ExchangeItemAllocation) SequenceMessageExt.getOperation(message)).getAllocatedItem(),
        getObject(SharedData));
    assertEquals(((ExchangeItemAllocation) SequenceMessageExt.getOperation(message)).getAllocatingInterface(),
        getObject(ITF_SHAREDDATA));

    SequenceMessage message2 = mustBeMonoTransitioned(SM2_SharedData);
    assertEquals(((ExchangeItemAllocation) SequenceMessageExt.getOperation(message2)).getAllocatedItem(),
        getObject(SharedData));
    assertEquals(((ExchangeItemAllocation) SequenceMessageExt.getOperation(message2)).getAllocatingInterface(),
        getObject(ITF_SHAREDDATA));

    SequenceMessage message3 = mustBeMonoTransitioned(SM3_SharedData);
    assertEquals(((ExchangeItemAllocation) SequenceMessageExt.getOperation(message3)).getAllocatedItem(),
        getObject(SharedData));
    assertEquals(((ExchangeItemAllocation) SequenceMessageExt.getOperation(message3)).getAllocatingInterface(),
        getObject(ITF_SHAREDDATA));

    SequenceMessage message4 = mustBeMonoTransitioned(SM1_Operation);
    assertEquals(((ExchangeItemAllocation) SequenceMessageExt.getOperation(message4)).getAllocatedItem(),
        getObject(Operation));
    assertEquals(((ExchangeItemAllocation) SequenceMessageExt.getOperation(message4)).getAllocatingInterface(),
        getObject(ITF_OPERATION));
  }

}
