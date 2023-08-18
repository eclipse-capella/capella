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

import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.helpers.interaction.services.SequenceMessageExt;
import org.polarsys.capella.core.data.information.ExchangeItemInstance;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class CreateRule_IS2IS_ItfEI_01 extends TopDownTransitionTestCase {

  public static final String IS_CAPABILITY_1 = "2fd6d4ed-9914-4fc5-8c71-8f0bbb589734"; //$NON-NLS-1$

  public static final String IR_SA_2 = "b23d70c3-3fe9-448b-a0b4-d11760a1ec93"; //$NON-NLS-1$
  public static final String IR_SYSTEM = "c4a3e94d-2b93-4999-9a4b-36d25b23b08c"; //$NON-NLS-1$
  public static final String IR_SharedData = "28944b85-3705-4d0b-9d6e-ae34e2eddbb3"; //$NON-NLS-1$
  public static final String IR_SA_3 = "16f44fde-3a8c-4c86-b371-4a0b7119f48e"; //$NON-NLS-1$

  public static final String SharedData = "17bbb80e-6757-4a07-95bf-8f607dfea043"; //$NON-NLS-1$
  public static final String Operation = "5988cb79-01d9-41a1-acbd-b856c20e9708"; //$NON-NLS-1$
  public static final String ITF_SHAREDDATA = "b8db26ed-c61a-40b4-9d9f-532249576a02"; //$NON-NLS-1$
  public static final String ITF_OPERATION = "fd44f440-e8d9-4de9-882f-407116886b0d"; //$NON-NLS-1$
  
  public static final String LC_1 = "5fb1991d-1048-41fc-ae28-6221f2680368"; //$NON-NLS-1$
  public static final String LC_2 = "21a3c2d7-67d0-4eec-adcb-d8ef86a8f01a"; //$NON-NLS-1$
  
  public static final String SM1_SharedData = "4ca5cdbf-1899-4d6e-9869-e5fbf165b5e8"; //$NON-NLS-1$
  public static final String SM2_SharedData = "4d2f5572-da98-4704-9cca-fdb36998e0d5"; //$NON-NLS-1$
  public static final String SM3_SharedData = "4ab91d02-d283-4ca9-a82b-e608444291af"; //$NON-NLS-1$
  public static final String SM1_Operation = "3954a786-73c8-4028-ae6c-5361a01cf8e5"; //$NON-NLS-1$
  
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, false);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, false);
    
    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITY_1)));

    //InstanceRole and all operations shall refer to the original ExchangeItems
    InstanceRole role = mustBeMonoTransitioned(IR_SharedData);
    assertTrue(((ExchangeItemInstance)role.getRepresentedInstance()).getAbstractType() == getObject(SharedData));
    
    SequenceMessage message = mustBeMonoTransitioned(SM1_SharedData);
    assertEquals(((ExchangeItemAllocation)SequenceMessageExt.getOperation(message)).getAllocatedItem(), getObject(SharedData));
    assertEquals(((ExchangeItemAllocation)SequenceMessageExt.getOperation(message)).getAllocatingInterface(), getObject(ITF_SHAREDDATA));

    SequenceMessage message2 = mustBeMonoTransitioned(SM2_SharedData);
    assertEquals(((ExchangeItemAllocation)SequenceMessageExt.getOperation(message2)).getAllocatedItem(), getObject(SharedData));
    assertEquals(((ExchangeItemAllocation)SequenceMessageExt.getOperation(message2)).getAllocatingInterface(), getObject(ITF_SHAREDDATA));

    SequenceMessage message3 = mustBeMonoTransitioned(SM3_SharedData);
    assertEquals(((ExchangeItemAllocation)SequenceMessageExt.getOperation(message3)).getAllocatedItem(), getObject(SharedData));
    assertEquals(((ExchangeItemAllocation)SequenceMessageExt.getOperation(message3)).getAllocatingInterface(), getObject(ITF_SHAREDDATA));
    
    SequenceMessage message4 = mustBeMonoTransitioned(SM1_Operation);
    assertEquals(((ExchangeItemAllocation)SequenceMessageExt.getOperation(message4)).getAllocatedItem(), getObject(Operation));
    assertEquals(((ExchangeItemAllocation)SequenceMessageExt.getOperation(message4)).getAllocatingInterface(), getObject(ITF_OPERATION));
  }

}
