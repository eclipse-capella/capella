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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 * - Rename InterfacePkg from Logical Architecture to Interfaces
 * - Create ExchangeItem into Interfaces named ExchangeItem 1
 * - Create ExchangeItemElement into ExchangeItem 1 to Class1 named ExchangeItemElement 1
 * - Rename LiteralNumericValue from ExchangeItemElement 1 to minCard
 * - Rename LiteralNumericValue from ExchangeItemElement 1 to maxCard
 * - Create ExchangeItemElement into ExchangeItem 1 to Class1_Final named ExchangeItemElement 2
 * - Rename LiteralNumericValue from ExchangeItemElement 2 to minCard
 * - Rename LiteralNumericValue from ExchangeItemElement 2 to maxCard
 * - Create Interface into Interfaces named Interface 1
 * - Create ExchangeItemAllocation into Interface 1 to ExchangeItem 1 named ExchangeItemAllocation
 * - Rename DataPkg from Logical Architecture to Data
 * - Create Class into Data named Class1
 * - Create Property into Class1 named P1
 * - Rename LiteralNumericValue from P1 to minCard
 * - Rename LiteralNumericValue from P1 to maxCard
 * - Create Class into Data named Class1_Final
 * - Create Property into Class1_Final named P2
 * - Rename LiteralNumericValue from P2 to minCard
 * - Rename LiteralNumericValue from P2 to maxCard
 * - Rename LogicalComponent from Logical Architecture to Logical System
 * - Create NumericType into Predefined Types from SA named Byte
 * - Rename LiteralNumericValue from Byte to min
 * - Rename LiteralNumericValue from Byte to max
 * - Create NumericType into Data named Double
 * - Create Property into Class1 named P3
 * - Rename LiteralNumericValue from P3 to minCard
 * - Rename LiteralNumericValue from P3 to maxCard
 * 
 * - Create LogicalActor into Actors named LogicalActor 1
 * - Create ComponentPort into LogicalActor 1 named ComponentPort1
 * - Create InterfaceUse into LogicalActor 1 linked to I1
 * - Create InterfaceImplementation into LogicalActor 1 linked to I2
 * 
 * 
 * Expected Result:\
 * - Performing LCPC transition of LS should transition I1, not I2
 * - Performing Actor transition of LogicalActor 1 should transition I1, not I2
 * 
 * </pre>
 */
public class Context_I01_02 extends TopDownTransitionTestCase {

  private String id_i1 = "01ea9994-3d8f-473d-8928-107552dbaa3e";
  private String id_exchangeitemallocation1 = "20fabc4e-7d20-4e18-9f52-b3344ddf0f56";
  private String id_i2 = "9ca32521-6382-4140-b00f-68ba163cdec7";
  private String id_logical_system = "0ed58c40-869f-4cba-9921-447b2df60db6";
  private String id_componentport1 = "2e0ae093-47fd-463e-b5f7-0a464e96d8c2";
  private String id_componentport2 = "4be7ff3f-7e54-4772-93c6-ee4355aaf12b";

  private String id_logicalactor_1 = "c036b2a0-a011-4c60-ba60-6c5278e5ad04";
  private String id_interfaceuse = "58d83b5c-0694-4c55-8b34-a7a5121f7b13";
  private String id_interfaceimplementation = "1976022a-1a2f-4fae-b6bf-16abe3885d2b";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, true);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_I01");
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
    step2();
  }

  private void step1() {
    performLCtoPCTransition(Arrays.asList(getObject(id_logical_system)));
    mustBeTransitioned(id_i1);
    shouldNotBeTransitioned(id_i2);

    mustBeTransitioned(id_componentport1);
    mustBeTransitioned(id_componentport2);
    mustBeTransitioned(id_logical_system);
    mustBeTransitioned(id_exchangeitemallocation1);
    mustBeTransitioned(id_i1);
  }

  private void step2() {
    performActorTransition(Arrays.asList(getObject(id_logicalactor_1)));
    AbstractNamedElement logicalactor_1t = (AbstractNamedElement) mustBeTransitioned(id_logicalactor_1);

    AbstractNamedElement i1t = (AbstractNamedElement) mustBeTransitioned(id_i1);

    Interface i2 = (Interface) shouldExist(id_i2);

    shouldNotBeTransitioned(id_i2);

    EObject interfaceuset = mustBeTransitioned(id_interfaceuse);

    // Check consistency between link
    assertTrue(NLS.bind("Element {0} should be linked to {1}", logicalactor_1t.getName(), i1t.getName()),
        i1t == ((InterfaceUse) interfaceuset).getUsedInterface());

    EObject interfaceimplementationt = mustBeTransitioned(id_interfaceimplementation);

    // Check consistency between link
    assertTrue(NLS.bind("Element {0} should be linked to {1}", logicalactor_1t.getName(), i1t.getName()),
        i2 == ((InterfaceImplementation) interfaceimplementationt).getImplementedInterface());
  }
}