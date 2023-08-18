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

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 * - Rename InterfacePkg from System Analysis to Interfaces
 * - Create ExchangeItem into Interfaces named EI1
 * - Create Interface into Interfaces named I1
 * - Create InterfacePkg into Interfaces named IP1
 * - Create ExchangeItem into IP1 named EI11
 * - Rename DataPkg from System Analysis to Data
 * - Create ExchangeItem into Data named DEI1
 * - Create DataPkg into Data named DP1
 * - Create ExchangeItem into DP1 named DEI11
 * - Create ExchangeItemAllocation into I1 to EI431
 * - Create ExchangeItemAllocation into I1 to EI41
 * - Create ExchangeItemAllocation into I1 to EI1
 * - Create ExchangeItemAllocation into I1 to EI11
 * 
 * 
 * Expected Result:\
 * Perform transition on I1
 * - I1 should allocate the exchange item in previous phase
 * Perform transition on EI11
 * - I1 should allocate the exchange item in previous phase, EI11 should be transitioned
 * Perform transition on I1
 * - I1 should allocate the exchange item in transitioned phase
 * 
 * </pre>
 */
public class UpdateRule_MEG01EI_01 extends TopDownTransitionTestCase {
  private String id_i1 = "764c540e-4c02-40fa-82a1-d8ccc3522933";
  private String id_ei11 = "ce94af92-1092-46c4-8457-a10a0635f0e2";
  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, false);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
    step2();
    step3();
  }

  private void step1() {
    performInterfaceTransition(Arrays.asList(getObject(id_i1)));
    Interface i1 = getObject(id_i1);
    assertNotNull(NLS.bind(Messages.NullElement, "I1"), i1);
    // I1 must be transitioned
    NamedElement i1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(i1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, i1.getName()), i1t);

    ExchangeItem ei11 = getObject(id_ei11);
    assertNotNull(NLS.bind(Messages.NullElement, "EI11"), ei11);
    // EI11 should not be transitioned
    NamedElement ei11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei11);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, ei11.getName()), ei11t);
  }

  private void step2() {
    performExchangeItemTransition(Arrays.asList(getObject(id_ei11)));
    Interface i1 = getObject(id_i1);
    assertNotNull(NLS.bind(Messages.NullElement, "I1"), i1);
    // I1 must be transitioned
    NamedElement i1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(i1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, i1.getName()), i1t);

    ExchangeItem ei11 = getObject(id_ei11);
    assertNotNull(NLS.bind(Messages.NullElement, "EI11"), ei11);
    // EI11 must be transitioned
    NamedElement ei11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei11.getName()), ei11t);

    boolean isTransitioned = true;
    for (ExchangeItemAllocation allocation : ((Interface) i1t).getOwnedExchangeItemAllocations()) {
      if (allocation.getAllocatedItem().equals(ei11t)) {
        isTransitioned = false;
      }
    }
    assertTrue(Messages.ShouldNotAllocateTransitionedEI, isTransitioned);
  }

  private void step3() {
    performInterfaceTransition(Arrays.asList(getObject(id_i1)));
    Interface i1 = getObject(id_i1);
    assertNotNull(NLS.bind(Messages.NullElement, "I1"), i1);
    // I1 must be transitioned
    NamedElement i1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(i1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, i1.getName()), i1t);

    ExchangeItem ei11 = getObject(id_ei11);
    assertNotNull(NLS.bind(Messages.NullElement, "EI11"), ei11);
    // EI11 must be transitioned
    NamedElement ei11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei11.getName()), ei11t);

    boolean isTransitioned = false;
    boolean isCurrent = false;
    for (ExchangeItemAllocation allocation : ((Interface) i1t).getOwnedExchangeItemAllocations()) {
      if (allocation.getAllocatedItem() != null) {
        if (allocation.getAllocatedItem().equals(ei11t)) {
          isTransitioned = true;
        } else if (allocation.getAllocatedItem().equals(ei11)) {
          isCurrent = true;
        }
      }

    }
    assertFalse(Messages.ShouldNotAllocateEI, isCurrent);
    assertTrue(Messages.ShouldAllocateTransitionedEI, isTransitioned);
  }

}
