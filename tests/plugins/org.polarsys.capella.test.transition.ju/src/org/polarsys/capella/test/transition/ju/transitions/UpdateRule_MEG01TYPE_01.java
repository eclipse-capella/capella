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
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
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
 * - Create ExchangeItem into Interfaces named EI1
 * - Create ExchangeItemElement into EI1 to BT1 named EIE11
 * - Rename LiteralNumericValue from EIE11 to LNV111
 * - Rename LiteralNumericValue from EIE11 to LNV111
 * - Create ExchangeItemElement into EI1 to NT2 named EIE12
 * - Rename LiteralNumericValue from EIE12 to LNV121
 * - Rename LiteralNumericValue from EIE12 to LNV121
 * - Rename DataPkg from Logical Architecture to Data
 * - Create DataPkg into Data named DP1
 * - Create BooleanType into DP1 named BT11
 * - Create Union into Data named U1
 * - Create BooleanType into Data named BT1
 * - Create NumericType into Data named NT2
 * 
 * 
 * Expected Result:\
 * Perform transition on EI1
 * - EI1 should allocate the type in previous phase
 * Perform transition on BT1
 * - EI1 should allocate the type in previous phase, BT1 should be transitioned
 * Perform transition on EI1
 * - EI1 should allocate the type in transitioned phase
 * 
 * </pre>
 */
public class UpdateRule_MEG01TYPE_01 extends TopDownTransitionTestCase {
  private String id_ei1 = "79a45b64-6d81-4ebe-986a-3c81e2da44ed";
  private String id_eie11 = "d90c7905-5f97-4aef-b12d-721ea19cc184";
  private String id_eie12 = "ea92a7f1-ac42-4a03-aec8-cee84294ddc2";
  private String id_bt11 = "4bcc4b14-2a5a-452a-80a3-343039270be9";
  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__DATATYPE, false);
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
    performExchangeItemTransition(Arrays.asList(getObject(id_ei1)));
    ExchangeItem ei1 = getObject(id_ei1);
    assertNotNull(NLS.bind(Messages.NullElement, "EI1"), ei1);
    // EI1 must be transitioned
    NamedElement ei1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei1.getName()), ei1t);

    ExchangeItemElement eie11 = getObject(id_eie11);
    assertNotNull(NLS.bind(Messages.NullElement, "EIE11"), eie11);
    // EIE11 must be transitioned
    NamedElement eie11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(eie11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, eie11.getName()), eie11t);

    ExchangeItemElement eie12 = getObject(id_eie12);
    assertNotNull(NLS.bind(Messages.NullElement, "EIE12"), eie12);
    // EIE12 must be transitioned
    NamedElement eie12t = (NamedElement) ProjectionTestUtils.getAllocatingElement(eie12);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, eie12.getName()), eie12t);

    BooleanType bt11 = getObject(id_bt11);
    assertNotNull(NLS.bind(Messages.NullElement, "BT11"), bt11);
    // BT11 should not be transitioned
    NamedElement bt11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(bt11);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, bt11.getName()), bt11t);
  }

  private void step2() {
    performDataTransition(Arrays.asList(getObject(id_bt11)));
    ExchangeItem ei1 = getObject(id_ei1);
    assertNotNull(NLS.bind(Messages.NullElement, "EI1"), ei1);
    // EI1 must be transitioned
    NamedElement ei1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei1.getName()), ei1t);

    ExchangeItemElement eie11 = getObject(id_eie11);
    assertNotNull(NLS.bind(Messages.NullElement, "EIE11"), eie11);
    // EIE11 must be transitioned
    NamedElement eie11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(eie11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, eie11.getName()), eie11t);

    ExchangeItemElement eie12 = getObject(id_eie12);
    assertNotNull(NLS.bind(Messages.NullElement, "EIE12"), eie12);
    // EIE12 must be transitioned
    NamedElement eie12t = (NamedElement) ProjectionTestUtils.getAllocatingElement(eie12);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, eie12.getName()), eie12t);

    BooleanType bt11 = getObject(id_bt11);
    assertNotNull(NLS.bind(Messages.NullElement, "BT11"), bt11);
    // BT11 must be transitioned
    NamedElement bt11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(bt11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, bt11.getName()), bt11t);

    boolean isTransitioned = true;
    for (ExchangeItemElement element : ei1.getOwnedElements()) {
      if ((element.getAbstractType() != null) && element.getAbstractType().equals(bt11t)) {
        isTransitioned = false;
      }
    }
    assertTrue(Messages.ShouldNotAllocateTransitionedDT, isTransitioned);
  }

  private void step3() {
    performExchangeItemTransition(Arrays.asList(getObject(id_ei1)));
    ExchangeItem ei1 = getObject(id_ei1);
    assertNotNull(NLS.bind(Messages.NullElement, "EI1"), ei1);
    // EI1 must be transitioned
    NamedElement ei1t = (NamedElement) ProjectionTestUtils.getAllocatingElement(ei1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, ei1.getName()), ei1t);

    ExchangeItemElement eie11 = getObject(id_eie11);
    assertNotNull(NLS.bind(Messages.NullElement, "EIE11"), eie11);
    // EIE11 must be transitioned
    NamedElement eie11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(eie11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, eie11.getName()), eie11t);

    ExchangeItemElement eie12 = getObject(id_eie12);
    assertNotNull(NLS.bind(Messages.NullElement, "EIE12"), eie12);
    // EIE12 must be transitioned
    NamedElement eie12t = (NamedElement) ProjectionTestUtils.getAllocatingElement(eie12);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, eie12.getName()), eie12t);

    BooleanType bt11 = getObject(id_bt11);
    assertNotNull(NLS.bind(Messages.NullElement, "BT11"), bt11);
    // BT11 must be transitioned
    NamedElement bt11t = (NamedElement) ProjectionTestUtils.getAllocatingElement(bt11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, bt11.getName()), bt11t);

    boolean isTransitioned = false;
    boolean isCurrent = false;
    for (ExchangeItemElement element : ((ExchangeItem) ei1t).getOwnedElements()) {
      if (element.getAbstractType() != null) {
        if (element.getAbstractType().equals(bt11t)) {
          isTransitioned = true;
        } else if (element.getAbstractType().equals(bt11)) {
          isCurrent = true;
        }
      }
    }
    assertFalse(Messages.ShouldNotAllocateDT, isCurrent);
    assertTrue(Messages.ShouldAllocateTransitionedDT, isTransitioned);
  }

}
