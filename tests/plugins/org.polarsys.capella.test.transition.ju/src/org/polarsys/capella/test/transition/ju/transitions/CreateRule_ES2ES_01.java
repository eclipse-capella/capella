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
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition CES2CIS
 * 
 *
 * - Rename DataPkg from System Analysis to Data - Create DataPkg into Data named Predefined Types - Create BooleanType
 * into Predefined Types named Boolean - Create LiteralBooleanValue into Boolean named True - Create LiteralBooleanValue
 * into Boolean named False - Create NumericType into Predefined Types named Byte - Rename LiteralNumericValue from Byte
 * to - Rename LiteralNumericValue from Byte to - Create StringType into Predefined Types named Char - Rename
 * LiteralNumericValue from Char to - Rename LiteralNumericValue from Char to - Create NumericType into Predefined Types
 * named Double - Create NumericType into Predefined Types named Float - Create NumericType into Predefined Types named
 * Hexadecimal - Rename LiteralNumericValue from Hexadecimal to - Rename LiteralNumericValue from Hexadecimal to -
 * Create NumericType into Predefined Types named Integer - Create NumericType into Predefined Types named Long - Create
 * NumericType into Predefined Types named LongLong - Create NumericType into Predefined Types named Short - Create
 * StringType into Predefined Types named String - Create NumericType into Predefined Types named UnsignedInteger -
 * Rename LiteralNumericValue from UnsignedInteger to - Create NumericType into Predefined Types named UnsignedShort -
 * Rename LiteralNumericValue from UnsignedShort to - Create NumericType into Predefined Types named UnsignedLong -
 * Rename LiteralNumericValue from UnsignedLong to - Create NumericType into Predefined Types named UnsignedLongLong -
 * Rename LiteralNumericValue from UnsignedLongLong to - Create SystemFunction into System Functions named Root System
 * Function - Create FunctionRealization into Root System Function to Root Operational Activity - Create SystemFunction
 * into Root System Function named SF1 - Create FunctionOutputPort into SF1 named FOP11 - Create FunctionOutputPort into
 * SF1 named FOP12 - Create SystemFunction into Root System Function named SF2 - Create FunctionInputPort into SF2 named
 * FIP21 - Create FunctionOutputPort into SF2 named FOP21 - Create SystemFunction into Root System Function named SF3 -
 * Create FunctionInputPort into SF3 named FIP31 - Create SystemFunction into Root System Function named SF4 - Create
 * FunctionInputPort into SF4 named FIP41 - Create FunctionOutputPort into SF4 named FOP41 - Create SystemFunction into
 * Root System Function named SF5 - Create FunctionInputPort into SF5 named FIP51 - Create FunctionOutputPort into SF5
 * named FOP51 - Create SystemFunction into Root System Function named SF6 - Create FunctionInputPort into SF6 named
 * FIP61 - Create FunctionalExchange into Root System Function named FE1 from FOP11(SF1) to FIP21(SF2) -> Linked to
 * ExchangeItem(EI1) - Create FunctionalExchange into Root System Function named FE2 from FOP21(SF2) to FIP31(SF3) ->
 * Linked to ExchangeItem(EI2) - Create FunctionalExchange into Root System Function named FE3 from FOP41(SF4) to
 * FIP61(SF6) - Create FunctionalExchange into Root System Function named FE4 from FOP12(SF1) to FIP51(SF5) -> Linked to
 * ExchangeItem(EI4) -> Linked to ExchangeItem(EI3) - Create FunctionalExchange into Root System Function named FE5 from
 * FOP51(SF5) to FIP41(SF4) - Rename ActorPkg from System Analysis to Actors - Create Actor into Actors named A1 -
 * Create ComponentFunctionalAllocation into A1 to SF2 - Create Actor into Actors named A2 - Create
 * ComponentFunctionalAllocation into A2 to SF3 - Create ComponentPort into A2 named CP21 - Create PortRealization into
 * CP21 to FIP31 - Create Actor into Actors named A3 - Create ComponentFunctionalAllocation into A3 to SF4 - Create
 * Actor into Actors named A4 - Create ComponentFunctionalAllocation into A4 to SF5 - Create ComponentPort into A4 named
 * CP41 - Create PortRealization into CP41 to FIP51 - Create ComponentPort into A4 named CP42 - Create PortRealization
 * into CP42 to FIP51 - Rename System from System Analysis to System - Create ComponentFunctionalAllocation into System
 * to SF1 - Create ComponentFunctionalAllocation into System to SF6 - Create StateMachine into System named SM1 - Create
 * Region into SM1 named R11 - Create Scenario into Capability 1 named S1 -> Create InstanceRole IR11 covering System ->
 * Create InstanceRole IR12 covering Part 3 -> Create InstanceRole IR13 covering Part 4 -> Start ME11 for message FE3
 * ASYNCHRONOUS_CALL from (IR12) -> Message FE3 linked to (FE3) -> End ME12 for message FE3 ASYNCHRONOUS_CALL from
 * (IR11) -> Start execution E11 from (ME12) -> Start for message FE4 SYNCHRONOUS_CALL from (IR11) -> Message FE4 linked
 * to (FE4) -> End for message FE4 SYNCHRONOUS_CALL from (IR13) -> Start execution from () -> Start for message FE4
 * REPLY from (IR13) -> Message FE4 linked to (FE4) -> End execution for () -> End for message FE4 REPLY from (IR11) ->
 * End execution E11 for (FE3)
 * 
 * Logical transition of actor/functions
 * 
 * - Rename LogicalComponent from Logical Architecture to Logical System - Create TransfoLink into Logical System named
 * - Create KeyValue into named - Create TransfoLink into Logical System named - Create KeyValue into named - Create
 * StateMachine into Logical System named SM1 - Create Region into SM1 named R11 - Create StateMachine into Logical
 * System named SM2 - Create Region into SM2 named R21 - Create Part into Logical System named part_P1 typed by LC1 -
 * Create Part into Logical System named part_P2 typed by LC2 - Create LogicalComponent into Logical System named LC1 -
 * Create ComponentFunctionalAllocation into LC1 to SF1 - Create LogicalComponent into Logical System named LC2 - Create
 * ComponentFunctionalAllocation into LC2 to SF6 - Create SystemRealization into Logical System to System
 * 
 * * Expected Result:\
 *
 * IR(System) should be transitioned to two IR(LC1), IR(LC2) Messages incoming and outgoing from IR should be links to
 * one of transitioned IR of IR(System)
 * 
 */
public class CreateRule_ES2ES_01 extends TopDownTransitionTestCase {
  private String id_s1 = "c1109b5b-6767-44d3-9f9d-76a9d044e00c";
  private String id_ir1 = "75ae1d55-7039-4b9c-acd7-9506ccc0eea9";
  private String id_ir2 = "68b1e7f8-3595-4c28-9ffa-4a4c0fa3543f";
  private String id_ir3 = "d29951ef-3aa3-4ad4-a046-d68c87e03a07";
  private String id_sm1 = "ed997754-4000-435f-9d87-ca6927e274fe";
  private String id_sm2 = "a0af25e0-468b-459a-a7b9-853600d1a0e7";
  private String id_part_p1 = "91eb4d9f-51bd-4585-9771-cf3273f0f5f0";
  public static String id_fe3 = "06d2b8f3-87c5-4b5d-9a0a-67d74373d40b";
  public static String id_fe4 = "f43ca66a-cf9d-4e0d-ac8e-32dae488e320";
  private String id_part_p2 = "696381c3-94a1-4252-b152-4db5069e47f2";
  public static String id_part_ls = "9cea6174-9089-4177-9be9-008f88ea41bb";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performEStoESTransition(Arrays.asList(getObject(id_s1)));
    InstanceRole ilc1 = null;
    InstanceRole ilc2 = null;

    Part lc1 = getObject(id_part_p1);
    assertNotNull(NLS.bind("The element {0} doesn't exist", "lc1"), id_part_p1);
    Part lc2 = getObject(id_part_p2);
    assertNotNull(NLS.bind("The element {0} doesn't exist", "lc2"), id_part_p2);

    mustBeTransitioned(id_s1);

    for (EObject obj : mustBeMultiTransitioned(id_ir1, 2)) {
      if (obj instanceof InstanceRole) {
        InstanceRole role = (InstanceRole) obj;
        if (role.getRepresentedInstance() == lc1) {
          ilc1 = role;
        } else if (role.getRepresentedInstance() == lc2) {
          ilc2 = role;
        }
      }
    }

    assertNotNull(NLS.bind("The element {0} doesn't exist", EObjectLabelProviderHelper.getText(ilc1)), ilc1);
    assertNotNull(NLS.bind("The element {0} doesn't exist", EObjectLabelProviderHelper.getText(ilc2)), ilc2);

    mustBeTransitioned(id_ir2);
    mustBeTransitioned(id_ir3);

    SequenceMessage m1 = (SequenceMessage) mustBeTransitioned(id_sm1);
    SequenceMessage m2 = (SequenceMessage) mustBeTransitioned(id_sm2);

    assertTrue(m1.getReceivingEnd().getCovered().equals(ilc2));
    assertTrue(m2.getSendingEnd().getCovered().equals(ilc1));
  }

}
