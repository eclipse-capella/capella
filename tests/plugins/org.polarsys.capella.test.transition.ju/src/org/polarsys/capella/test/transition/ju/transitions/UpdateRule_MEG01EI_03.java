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
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.fa.ComponentExchange;
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
 * - Rename InterfacePkg from Logical Architecture to Interfaces
 * - Create ExchangeItem into Interfaces named EI1
 * - Create ExchangeItem into Interfaces named EI2
 * - Rename LogicalComponent from Logical Architecture to Logical System
 * - Create Connection into Logical System named C1 from CP11(part_P1) to CP21(part_P2)
 *    -> Linked to ExchangeItem(EI2)
 * - Create ConnectionEnd into C1CP11(part_P1)
 * - Create ConnectionEnd into C1CP21(part_P2)
 * - Create Part into Logical System named part_P1 typed by LC1
 * - Create Part into Logical System named part_P2 typed by LC2
 * - Create LogicalComponent into Logical System named LC1
 * 
 * Expected Result:\
 * Perform transition on C1
 * - C1 should allocate the exchange item EI2 in previous phase
 * Perform transition on EI2
 * - EI2 should allocate the exchange item in previous phase, C1 should be transitioned
 * Perform transition on C1
 * - C1 should allocate the exchange item in transitioned phase
 * 
 * </pre>
 */
public class UpdateRule_MEG01EI_03 extends TopDownTransitionTestCase {
  private String id_c1 = "9af1fdab-b4e1-440a-a599-9d17558af88e";
  private String id_ei2 = "d32b03ed-9b09-4e4d-a516-17ac6bfed086";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, false);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(UpdateRule_MEG01EI_01.class.getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
    step2();
    step3();
  }

  private void step1() {
    performLCtoPCTransition(Arrays.asList(getObject(id_c1)));
    ComponentExchange c1 = getObject(id_c1);
    assertNotNull(NLS.bind(Messages.NullElement, "C1"), c1);
    // C1 must be transitioned
    NamedElement c1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(c1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, c1.getName()), c1t);

    ExchangeItem ei2 = getObject(id_ei2);
    assertNotNull(NLS.bind(Messages.NullElement, "EI2"), ei2);
    // EI2 should not be transitioned
    NamedElement ei2t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei2);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, ei2.getName()), ei2t);
  }

  private void step2() {
    performExchangeItemTransition(Arrays.asList(getObject(id_ei2)));
    ComponentExchange c1 = getObject(id_c1);
    assertNotNull(NLS.bind(Messages.NullElement, "C1"), c1);
    // C1 must be transitioned
    NamedElement c1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(c1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, c1.getName()), c1t);

    ExchangeItem ei2 = getObject(id_ei2);
    assertNotNull(NLS.bind(Messages.NullElement, "EI2"), ei2);
    // EI2 must be transitioned
    NamedElement ei2t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei2);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei2.getName()), ei2t);

    boolean isTransitioned = true;
    for (AbstractExchangeItem item : ((ComponentExchange) c1t).getConvoyedInformations()) {
      if (item.equals(ei2t)) {
        isTransitioned = false;
      }
    }
    assertTrue(Messages.ShouldNotAllocateTransitionedEI, isTransitioned);
  }

  private void step3() {
    performLCtoPCTransition(Arrays.asList(getObject(id_c1)));
    ComponentExchange c1 = getObject(id_c1);
    assertNotNull(NLS.bind(Messages.NullElement, "C1"), c1);
    // C1 must be transitioned
    NamedElement c1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(c1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, c1.getName()), c1t);

    ExchangeItem ei2 = getObject(id_ei2);
    assertNotNull(NLS.bind(Messages.NullElement, "EI2"), ei2);
    // EI2 must be transitioned
    NamedElement ei2t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei2);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei2.getName()), ei2t);

    boolean isTransitioned = false;
    boolean isCurrent = false;
    for (AbstractExchangeItem item : ((ComponentExchange) c1t).getConvoyedInformations()) {
      if (item.equals(ei2t)) {
        isTransitioned = true;
      } else if (item.equals(ei2)) {
        isCurrent = true;
      }
    }
    assertFalse(Messages.ShouldNotAllocateEI, isCurrent);
    assertTrue(Messages.ShouldAllocateTransitionedEI, isTransitioned);
  }

}
