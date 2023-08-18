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
import org.polarsys.capella.core.data.fa.FunctionalExchange;
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
 * - Create SystemFunction into System Functions named SF1
 * - Create FunctionRealization into SF1 to Root Operational Activity
 * - Create SystemFunction into SF1 named SF11
 * - Create FunctionOutputPort into SF11 named FOP111
 * - Create SystemFunction into SF1 named SF12
 * - Create FunctionInputPort into SF12 named FIP121
 * - Create FunctionalExchange into SF1 named FE11 from FOP111(SF11) to FIP121(SF12)
 *    -> Linked to ExchangeItem(EI2)
 * 
 * - Rename InterfacePkg from System Analysis to Interfaces
 * - Create ExchangeItem into Interfaces named EI1
 * - Create ExchangeItem into Interfaces named EI2
 * 
 * Expected Result:\
 * Perform transition on FE11
 * - FE11 should allocate the exchange item EI2 in previous phase
 * Perform transition on EI2
 * - EI2 should allocate the exchange item in previous phase, FE11 should be transitioned
 * Perform transition on FE11
 * - FE11 should allocate the exchange item in transitioned phase
 * 
 * </pre>
 */
public class UpdateRule_MEG01EI_02 extends TopDownTransitionTestCase {
  private String id_fe11 = "1e7af6e2-e892-4491-b238-73ff89b53b54";
  private String id_ei2 = "93146c8b-9782-4731-9a52-5bbffda0bbc0";

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
    performFunctionalTransition(Arrays.asList(getObject(id_fe11)));
    FunctionalExchange fe11 = getObject(id_fe11);
    assertNotNull(NLS.bind(Messages.NullElement, "FE11"), fe11);
    // FE11 must be transitioned
    NamedElement fe11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(fe11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, fe11.getName()), fe11t);

    ExchangeItem ei2 = getObject(id_ei2);
    assertNotNull(NLS.bind(Messages.NullElement, "EI2"), ei2);
    // EI2 should not be transitioned
    NamedElement ei2t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei2);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, ei2.getName()), ei2t);
  }

  private void step2() {
    performExchangeItemTransition(Arrays.asList(getObject(id_ei2)));
    FunctionalExchange fe11 = getObject(id_fe11);
    assertNotNull(NLS.bind(Messages.NullElement, "FE11"), fe11);
    // FE11 must be transitioned
    NamedElement fe11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(fe11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, fe11.getName()), fe11t);

    ExchangeItem ei2 = getObject(id_ei2);
    assertNotNull(NLS.bind(Messages.NullElement, "EI2"), ei2);
    // EI2 must be transitioned
    NamedElement ei2t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei2);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei2.getName()), ei2t);

    boolean isTransitioned = true;
    for (AbstractExchangeItem item : ((FunctionalExchange) fe11t).getExchangedItems()) {
      if (item.equals(ei2t)) {
        isTransitioned = false;
      }
    }
    assertTrue(Messages.ShouldNotAllocateTransitionedEI, isTransitioned);
  }

  private void step3() {
    performFunctionalTransition(Arrays.asList(getObject(id_fe11)));
    FunctionalExchange fe11 = getObject(id_fe11);
    assertNotNull(NLS.bind(Messages.NullElement, "FE11"), fe11);
    // FE11 must be transitioned
    NamedElement fe11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(fe11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, fe11.getName()), fe11t);

    ExchangeItem ei2 = getObject(id_ei2);
    assertNotNull(NLS.bind(Messages.NullElement, "EI2"), ei2);
    // EI2 must be transitioned
    NamedElement ei2t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei2);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei2.getName()), ei2t);

    boolean isTransitioned = false;
    boolean isCurrent = false;
    for (AbstractExchangeItem item : ((FunctionalExchange) fe11t).getExchangedItems()) {
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
