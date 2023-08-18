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
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.helpers.information.services.ExchangeItemExt;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition CES2CIS Rename DataPkg from System Analysis to Data - Create DataPkg into Data named
 * Predefined Types - Create BooleanType into Predefined Types named Boolean - Create LiteralBooleanValue into Boolean
 * named True - Create LiteralBooleanValue into Boolean named False - Create NumericType into Predefined Types named
 * Byte - Rename LiteralNumericValue from Byte to - Rename LiteralNumericValue from Byte to - Create StringType into
 * Predefined Types named Char - Rename LiteralNumericValue from Char to - Rename LiteralNumericValue from Char to -
 * Create NumericType into Predefined Types named Double - Create NumericType into Predefined Types named Float - Create
 * NumericType into Predefined Types named Hexadecimal - Rename LiteralNumericValue from Hexadecimal to - Rename
 * LiteralNumericValue from Hexadecimal to - Create NumericType into Predefined Types named Integer - Create NumericType
 * into Predefined Types named Long - Create NumericType into Predefined Types named LongLong - Create NumericType into
 * Predefined Types named Short - Create StringType into Predefined Types named String - Create NumericType into
 * Predefined Types named UnsignedInteger - Rename LiteralNumericValue from UnsignedInteger to - Create NumericType into
 * Predefined Types named UnsignedShort - Rename LiteralNumericValue from UnsignedShort to - Create NumericType into
 * Predefined Types named UnsignedLong - Rename LiteralNumericValue from UnsignedLong to - Create NumericType into
 * Predefined Types named UnsignedLongLong - Rename LiteralNumericValue from UnsignedLongLong to - Create SystemFunction
 * into System Functions named Root System Function - Create FunctionRealization into Root System Function to Root
 * Operational Activity - Create SystemFunction into Root System Function named SF1 - Create FunctionInputPort into SF1
 * named FIP11 - Create FunctionOutputPort into SF1 named FOP11 - Create FunctionOutputPort into SF1 named FOP12 -
 * Create SystemFunction into Root System Function named SF2 - Create FunctionInputPort into SF2 named FIP21 - Create
 * FunctionOutputPort into SF2 named FOP21 - Create SystemFunction into Root System Function named SF3 - Create
 * FunctionInputPort into SF3 named FIP31 - Create SystemFunction into Root System Function named SF4 - Create
 * FunctionOutputPort into SF4 named FOP41 - Create SystemFunction into Root System Function named SF5 - Create
 * FunctionInputPort into SF5 named FIP51 - Create FunctionalExchange into Root System Function named FE1 from
 * FOP11(SF1) to FIP21(SF2) -> Linked to ExchangeItem(EI1) - Create FunctionalExchange into Root System Function named
 * FE2 from FOP21(SF2) to FIP31(SF3) -> Linked to ExchangeItem(EI2) - Create FunctionalExchange into Root System
 * Function named FE3 from FOP41(SF4) to FIP11(SF1) - Create FunctionalExchange into Root System Function named FE4 from
 * FOP12(SF1) to FIP51(SF5) -> Linked to ExchangeItem(EI4) -> Linked to ExchangeItem(EI3) - Rename ActorPkg from System
 * Analysis to Actors - Create Actor into Actors named A1 - Create ComponentFunctionalAllocation into A1 to SF2 - Create
 * ComponentPort into A1 named CP11 - Create PortRealization into CP11 to FIP21 - Create ComponentPort into A1 named
 * CP12 - Create PortRealization into CP12 to FOP21 - Create ComponentPort into A1 named CP13 - Create PortRealization
 * into CP13 to FIP21 - Create ComponentPort into A1 named CP14 - Create PortRealization into CP14 to FIP21 - Create
 * ComponentPort into A1 named CP15 - Create PortRealization into CP15 to FIP21 - Create Actor into Actors named A2 -
 * Create ComponentFunctionalAllocation into A2 to SF3 - Create ComponentPort into A2 named CP21 - Create
 * PortRealization into CP21 to FIP31 - Create Actor into Actors named A3 - Create ComponentFunctionalAllocation into A3
 * to SF4 - Create Actor into Actors named A4 - Create ComponentFunctionalAllocation into A4 to SF5 - Create
 * ComponentPort into A4 named CP41 - Create PortRealization into CP41 to FIP51 - Create ComponentPort into A4 named
 * CP42 - Create PortRealization into CP42 to FIP51 * - Rename System from System Analysis to System - Create
 * ComponentFunctionalAllocation into System to SF1 - Create StateMachine into System named SM1 - Create Region into SM1
 * named R11 - Create ComponentPort into System named CP1 - Create PortRealization into CP1 to FOP11 - Create
 * ComponentPort into System named CP2 - Create PortRealization into CP2 to FOP11 - Create ComponentPort into System
 * named CP3 - Create PortRealization into CP3 to FOP11 - Create ComponentPort into System named CP4 - Create
 * PortRealization into CP4 to FOP12 - Create ComponentPort into System named CP5 - Create PortRealization into CP5 to
 * FOP12 - Create ComponentPort into System named CP6 - Create PortRealization into CP6 to FOP11 - Create Capability
 * into Capabilities named Capability 1 - Create Scenario into Capability 1 named S1 -> Create InstanceRole IR11
 * covering System -> Create InstanceRole IR12 covering Part 1 -> Create InstanceRole IR13 covering Part 2 -> Create
 * InstanceRole IR14 covering Part 3 -> Create InstanceRole IR15 covering Part 4 -> Start ME11 for message SM11
 * SYNCHRONOUS_CALL from (IR11) -> Message SM11 linked to (FE1) -> End ME12 for message SM11 SYNCHRONOUS_CALL from
 * (IR12) -> Start execution E11 from (ME12) -> Start ME13 for message SM12 SYNCHRONOUS_CALL from (IR12) -> Message SM12
 * linked to (FE2) -> End ME14 for message SM12 SYNCHRONOUS_CALL from (IR13) -> Start execution E12 from (ME14) -> Start
 * ME15 for message SM13 REPLY from (IR13) -> Message SM13 linked to (FE2) -> End execution E12 for (ME15) -> End ME16
 * for message SM13 REPLY from (IR12) -> Start ME17 for message SM14 REPLY from (IR12) -> Message SM14 linked to (FE1)
 * -> End execution E11 for (ME17) -> End ME18 for message SM14 REPLY from (IR11) -> Start ME19 for message SM15
 * SYNCHRONOUS_CALL from (IR14) -> Message SM15 linked to (FE3) -> End ME110 for message SM15 SYNCHRONOUS_CALL from
 * (IR11) -> Start execution E13 from (ME110) -> Start ME111 for message SM16 REPLY from (IR11) -> Message SM16 linked
 * to (FE3) -> End execution E13 for (ME111) -> End ME112 for message SM16 REPLY from (IR14) -> Start ME113 for message
 * SM17 ASYNCHRONOUS_CALL from (IR11) -> Message SM17 linked to (FE4) -> End ME114 for message SM17 ASYNCHRONOUS_CALL
 * from (IR15) -> Start execution E14 from (ME114) -> End execution E14 for (SM17) - Create ActorCapabilityInvolvement
 * into Capability 1 named - Create ActorCapabilityInvolvement into Capability 1 named - Create
 * ActorCapabilityInvolvement into Capability 1 named - Create ActorCapabilityInvolvement into Capability 1 named -
 * Rename SystemCapabilityInvolvement from Capability 1 to Expected Result:\ - Scenario should be created - First and
 * second message should be transitioned to one message - Third message should be transitioned to 0 message - Fourth
 * message should be transitioned to two message - Interfaces should allocates exchanges items Test 2, test if a message
 * (FE/CE) between same component generate interface - Create FunctionInputPort into SF1 named FunctionInputPort -
 * Create FunctionOutputPort into SF1 named FunctionOutputPort - Create FunctionalExchange into SF1 named Hop from
 * FunctionOutputPort(SF1) to FunctionInputPort(SF1) -> Linked to ExchangeItem(EI3) - Create Capability into
 * CapabilityPkg 1 named C1 - Create Scenario into C1 named S11 -> Create InstanceRole IR111 covering System -> Start
 * ME111 for message SM111 SYNCHRONOUS_CALL from (IR111) -> Message SM111 linked to (Hop) -> End ME112 for message SM111
 * SYNCHRONOUS_CALL from (IR111) -> Start execution E111 from (ME112) -> Start ME113 for message SM112 REPLY from
 * (IR111) -> Message SM112 linked to (Hop) -> End execution E111 for (ME113) -> End ME114 for message SM112 REPLY from
 * (IR111) - Create ComponentExchange into System named sss from OutFlowPort(System) to InFlowPort(System) -> Linked to
 * ExchangeItem(EI4) -> Linked to ExchangeItem(EI2) - Create Scenario into C1 named S12 -> Create InstanceRole IR121
 * covering System -> Start ME121 for message SM121 SYNCHRONOUS_CALL from (IR121) -> Message SM121 linked to (sss) ->
 * End ME122 for message SM121 SYNCHRONOUS_CALL from (IR121) -> Start execution E121 from (ME122) -> Start ME123 for
 * message SM122 REPLY from (IR121) -> Message SM122 linked to (sss) -> End execution E121 for (ME123) -> End ME124 for
 * message SM122 REPLY from (IR121)
 */
public class CreateRule_ES2IS_01 extends TopDownTransitionTestCase {
  private String id_s1 = "917ebc96-17ac-4282-ac36-8ebfaa2791f0";
  private String id_ir12 = "61b567c8-3cbe-4d71-b3a3-a3442c007344";
  private String id_ir13 = "02eaadc2-a0b5-442d-a48b-4740516aae62";
  private String id_ir14 = "9eb02c4c-50cd-457c-809b-3d1ae0705ba8";
  private String id_ir15 = "33ff07d9-14e0-4596-ad38-ade7df87ae98";
  private String id_sm11 = "6d87fa04-1d3f-42bf-be7a-5fb11745c47d";
  private String id_sm12 = "396e235d-13ec-407d-8a81-b10aeb0199c0";
  private String id_sm13 = "ca41646d-fcca-4553-a438-a6fe14f68998";
  private String id_sm14 = "9392af62-6a8a-4200-970e-a37baabcb5c3";
  private String id_sm15 = "c4963e39-8548-4fd2-98e7-1f483ace18a2";
  private String id_sm16 = "8c03dcaf-6804-44e5-b61e-9835e544b32a";
  private String id_sm17 = "bead918d-df7c-48d2-85fd-53b2001ffd84";
  private String id_ei1 = "1b4dc8de-9605-4c4f-b62c-5a28ad55cfe9";
  private String id_ei2 = "905e87d4-b321-478e-9b6f-006084b47720";
  private String id_ei3 = "bf212edf-bf56-4ed0-96fa-05f29e1343a7";
  private String id_ei4 = "c73ab0af-221c-4654-bce2-06e33427f08c";

  // Test2
  private String id_hop = "ee8b3307-d95d-4ee0-9f6d-7b8c52e1c51e";
  private String id_sss = "05504f84-666c-4959-b40f-e67c23faa9ba";
  private String id_s11 = "f5e1d590-9b61-4060-b071-77e2301961c6";
  private String id_s12 = "f501020f-988e-476d-8744-d906f08d2184";
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
  }

  private void step1() {
    performEStoISTransition(Arrays.asList(getObject(id_s1)));
    mustBeTransitioned(id_s1);
    // mustBeTransitioned("IR11", _id_ir11);
    mustBeTransitioned(id_ir12);
    mustBeTransitioned(id_ir13);
    mustBeTransitioned(id_ir14);
    mustBeTransitioned(id_ir15);

    mustBeTransitioned(id_sm11);
    mustBeTransitioned(id_sm12);
    mustBeTransitioned(id_sm13);
    mustBeTransitioned(id_sm14);
    shouldNotBeTransitioned(id_sm15);
    shouldNotBeTransitioned(id_sm16);

    // Two message with exchanges items for a functional exchange
    List<EObject> a4t = mustBeMultiTransitioned(id_sm17, 2);

    // Message with event should not be inverted
    assertFalse(NLS.bind("Message ''{0}'' should be inverted", EObjectLabelProviderHelper.getText(a4t.get(0))),
        ((SequenceMessage) a4t.get(0)).getSendingEnd().getCovered() != ((SequenceMessage) a4t.get(1)).getSendingEnd()
            .getCovered());

    // Exchange items should be allocated into interfaces
    shouldBeAllocatedToInterface("ei1", id_ei1);
    shouldBeAllocatedToInterface("ei2", id_ei2);
    shouldBeAllocatedToInterface("ei3", id_ei3);
    shouldBeAllocatedToInterface("ei4", id_ei4);
  }

  private void shouldBeAllocatedToInterface(String string_p, String idE11_p) {
    TraceableElement e11 = getObject(idE11_p);
    assertNotNull(NLS.bind(Messages.NullElement, string_p), e11);
    assertTrue(NLS.bind("Exchange item ''{0}'' should be allocated", EObjectLabelProviderHelper.getText(e11)),
        ExchangeItemExt.getRelatedExchangeItemAllocations((ExchangeItem) e11).size() != 0);
  }

  private void step2() {
    performEStoISTransition(Arrays.asList(getObject(id_s11)));
    mustBeTransitioned(id_s11);
    // mustBeTransitioned("_id_ir111", id_ir111);

    mustBeTransitionedTo(id_hop, CsPackage.Literals.INTERFACE);
  }

  private void step3() {
    performEStoISTransition(Arrays.asList(getObject(id_s12)));
    mustBeTransitioned(id_s12);
    // mustBeTransitioned("_id_ir121", id_ir121);

    mustBeTransitionedTo(id_sss, CsPackage.Literals.INTERFACE);
  }

}
