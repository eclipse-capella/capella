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
import java.util.stream.Collectors;

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

public class CreateRule_IS2IS_MultiInstanceRoles extends TopDownTransitionTestCase {

  public static final String IS_CAPABILITY_1 = "04c93959-899f-4b0d-b617-541d585b7313"; //$NON-NLS-1$
  public static final String IR_SA_2 = "097b6533-a367-459c-a9b0-4bcbf4dd8d01"; //$NON-NLS-1$
  public static final String IR_SYSTEM = "447cccb3-69e7-49a2-a3fc-68e87f2e5129"; //$NON-NLS-1$
  public static final String IR_SharedData = "beb2a693-d628-4b41-861c-91df504142da"; //$NON-NLS-1$
  public static final String IR_SA_3 = "ed660352-0bed-4556-8529-9b6d372b8d4d"; //$NON-NLS-1$
  
  public static final String SharedData = "bddb2f3f-c9b8-44ea-a1b1-ff00c9611880"; //$NON-NLS-1$
  public static final String Operation = "e8d8333f-99e3-4b2e-b429-10f02ac5bf9e"; //$NON-NLS-1$
  public static final String ITF_SharedData = "a6a75d5c-db12-4d29-b81a-eb659738d557"; //$NON-NLS-1$
  public static final String ITF_Operation = "bb23187e-3773-4016-9179-397f7b549659"; //$NON-NLS-1$
  
  public static final String LC_1 = "17446913-20c5-4a60-ad81-7bb313262550"; //$NON-NLS-1$
  public static final String LC_2 = "6b08af0f-6d6b-4dbd-baac-ac5ff72bcc69"; //$NON-NLS-1$
  
  public static final String SM1_SharedData = "b9ded4e5-fd33-40e3-b8ae-0f0ba818c973"; //$NON-NLS-1$
  public static final String SM2_SharedData = "d98be497-557c-4d2e-a53a-eddfe0adf727"; //$NON-NLS-1$
  public static final String SM3_SharedData = "8ed3198b-e4e1-4450-a893-56d4331392a2"; //$NON-NLS-1$
  public static final String SM4_Operation = "a6314248-45f1-4d4a-b1bb-404973027919"; //$NON-NLS-1$
  
  public static final String SA_2 = "4f2f62e3-603f-4b9b-8d6d-15300c8454eb"; //$NON-NLS-1$
  public static final String SA_3 = "42d365d4-a8f0-4337-b695-792d15906dfe"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }
  
  @Override
  public void performTest() throws Exception {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, false);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, false);
    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITY_1)));

    // System shall be splitted into 2 instance roles
    List<InstanceRole> roles = mustBeMultiTransitioned(IR_SYSTEM, 2);
    List<Component> cpts = roles.stream().map(x -> (Component) x.getRepresentedInstance().getAbstractType())
        .collect(Collectors.toList());
    assertTrue(cpts.contains(getObject(LC_1)));
    assertTrue(cpts.contains(getObject(LC_2)));

    mustBeMonoTransitioned(IR_SA_2);
    mustBeMonoTransitioned(IR_SA_3);
    mustBeMonoTransitioned(IR_SharedData);

    assertTrue(getSource(SM1_SharedData) == getObject(LC_1));
    assertTrue(getTarget(SM1_SharedData) == getObject(SA_3));
    assertTrue(getSource(SM2_SharedData) == getObject(LC_1));
    assertTrue(getTarget(SM2_SharedData) == getObject(SharedData));
    assertTrue(getSource(SM4_Operation) == getObject(SA_2));
    assertTrue(getTarget(SM4_Operation) == getObject(LC_2));
  }
  
  protected AbstractType getSource(String sm) {
    return ((SequenceMessage) mustBeMonoTransitioned(sm)).getSendingEnd().getCoveredInstanceRoles().get(0).getRepresentedInstance().getType();
  }

  protected AbstractType getTarget(String sm) {
    return ((SequenceMessage) mustBeMonoTransitioned(sm)).getReceivingEnd().getCoveredInstanceRoles().get(0).getRepresentedInstance().getType();
  }

}
