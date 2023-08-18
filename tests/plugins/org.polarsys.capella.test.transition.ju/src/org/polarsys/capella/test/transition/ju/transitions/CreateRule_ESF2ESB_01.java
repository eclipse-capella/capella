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
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.interaction.EventReceiptOperation;
import org.polarsys.capella.core.data.interaction.EventSentOperation;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioKind;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test Scenario transition CES2CIS
 * 
 *
 * - Rename InterfacePkg from System Analysis to Interfaces - Create ExchangeItem into Interfaces named EI1 - Create
 * ExchangeItem into Interfaces named EI2 - Create ExchangeItem into Interfaces named EI3 - Create ExchangeItem into
 * Interfaces named EI4 - Rename SystemFunctionPkg from System Analysis to System Functions - Create SystemFunction into
 * System Functions named Root System Function - Create FunctionRealization into Root System Function to Root
 * Operational Activity - Create SystemFunction into Root System Function named SF1 - Create FunctionInputPort into SF1
 * named FIP11 - Create FunctionOutputPort into SF1 named FOP11 - Create FunctionOutputPort into SF1 named FOP12 -
 * Create SystemFunction into Root System Function named SF2 - Create FunctionInputPort into SF2 named FIP21 - Create
 * FunctionOutputPort into SF2 named FOP21 - Create SystemFunction into Root System Function named SF3 - Create
 * FunctionInputPort into SF3 named FIP31 - Create SystemFunction into Root System Function named SF4 - Create
 * FunctionOutputPort into SF4 named FOP41 - Create SystemFunction into Root System Function named SF5 - Create
 * FunctionInputPort into SF5 named FIP51 - Create SystemFunction into Root System Function named SF6 - Create
 * FunctionOutputPort into SF6 named FOP61 - Create SystemFunction into Root System Function named SF7 - Create
 * FunctionInputPort into SF7 named FIP71 - Create FunctionalExchange into Root System Function named FE1 from
 * FOP11(SF1) to FIP21(SF2) -> Linked to ExchangeItem(EI1) - Create FunctionalExchange into Root System Function named
 * FE2 from FOP21(SF2) to FIP31(SF3) -> Linked to ExchangeItem(EI2) - Create FunctionalExchange into Root System
 * Function named FE3 from FOP41(SF4) to FIP11(SF1) -> Linked to ExchangeItem(EI3) - Create FunctionalExchange into Root
 * System Function named FE4 from FOP12(SF1) to FIP51(SF5) -> Linked to ExchangeItem(EI4) -> Linked to ExchangeItem(EI3)
 * - Create FunctionalExchange into Root System Function named FE5 from FOP61(SF6) to FIP71(SF7)
 * 
 * * - Rename ActorPkg from System Analysis to Actors - Create Actor into Actors named A1 - Create
 * ComponentFunctionalAllocation into A1 to SF2 - Create Actor into Actors named A2 - Create
 * ComponentFunctionalAllocation into A2 to SF3 - Create Actor into Actors named A3 - Create
 * ComponentFunctionalAllocation into A3 to SF4 - Create Actor into Actors named A4 - Create
 * ComponentFunctionalAllocation into A4 to SF5 - Create Actor into Actors named A5 - Create
 * ComponentFunctionalAllocation into A5 to SF6 - Create ComponentPort into A5 named CP51 - Create PortRealization into
 * CP51 to FOP61 - Create ComponentPort into A5 named CP52 - Create PortRealization into CP52 to FOP61 - Create Actor
 * into Actors named A6 - Create ComponentFunctionalAllocation into A6 to SF7 - Create ComponentPort into A6 named CP61
 * - Create PortRealization into CP61 to FIP71 - Create ComponentPort into A6 named CP62 - Create PortRealization into
 * CP62 to FIP71
 * 
 * 
 * - Rename System from System Analysis to System - Create ComponentFunctionalAllocation into System to SF1 - Create
 * StateMachine into System named SM1 - Create Region into SM1 named R11
 * 
 * * - Create Scenario into Capability 1 named S1 -> Create InstanceRole IR11 covering System -> Create InstanceRole
 * IR12 covering Part 1 -> Create InstanceRole IR13 covering Part 3 -> Start ME11 for message SM11 SYNCHRONOUS_CALL from
 * (IR13) -> Message SM11 linked to (FE3) -> End ME12 for message SM11 SYNCHRONOUS_CALL from (IR11) -> Start execution
 * E11 from (ME12) -> Start ME13 for message SM12 SYNCHRONOUS_CALL from (IR11) -> Message SM12 linked to (FE1) -> End
 * ME14 for message SM12 SYNCHRONOUS_CALL from (IR12) -> Start execution E12 from (ME14) -> Start ME15 for message SM13
 * REPLY from (IR12) -> Message SM13 linked to (FE1) -> End execution E12 for (ME15) -> End ME16 for message SM13 REPLY
 * from (IR11) -> Start ME17 for message SM14 REPLY from (IR11) -> Message SM14 linked to (FE3) -> End execution E11 for
 * (ME17) -> End ME18 for message SM14 REPLY from (IR13) - Create Scenario into Capability 1 named S2 -> Create
 * InstanceRole IR21 covering Part 5 -> Create InstanceRole IR22 covering Part 6 -> Start ME21 for message SM21
 * ASYNCHRONOUS_CALL from (IR21) -> Message SM21 linked to (Exchange 1) -> End ME22 for message SM21 ASYNCHRONOUS_CALL
 * from (IR22) -> Start execution E21 from (ME22) -> End execution E21 for (SM21) -> Start ME24 for message SM22
 * SYNCHRONOUS_CALL from (IR21) -> Message SM22 linked to (Exchange 1) -> End ME25 for message SM22 SYNCHRONOUS_CALL
 * from (IR22) -> Start execution E22 from (ME25) -> Start ME26 for message SM23 REPLY from (IR22) -> Message SM23
 * linked to (Exchange 1) -> End execution E22 for (ME26) -> End ME27 for message SM23 REPLY from (IR21) -> Start ME28
 * for message SM24 SYNCHRONOUS_CALL from (IR21) -> Message SM24 linked to (Exchange 1) -> End ME29 for message SM24
 * SYNCHRONOUS_CALL from (IR22) -> Start execution E23 from (ME29) -> Start ME210 for message SM25 REPLY from (IR22) ->
 * Message SM25 linked to (Exchange 1) -> End execution E23 for (ME210) -> End ME211 for message SM25 REPLY from (IR21)
 * 
 * - Create Connection into System Context named Exchange 1 from CP51(A5) to CP61(A6) - Create
 * ConnectionFunctionalExchangeAllocation into Exchange 1 to FE5 - Create Connection into System Context named C 1 from
 * CP52(Part 5) to CP62(Part 6) - Create ConnectionFunctionalExchangeAllocation into C 1 to FE5 - Create ConnectionEnd
 * into C 1 CP52(Part 5) - Create ConnectionEnd into C 1 CP62(Part 6) - Create Part into System Context named System
 * typed by System - Create Part into System Context named Part 1 typed by A1 - Create Part into System Context named
 * Part 2 typed by A2 - Create Part into System Context named Part 3 typed by A3 - Create Part into System Context named
 * Part 4 typed by A4 - Create Part into System Context named Copy of Part 4 typed by A4 - Create Part into System
 * Context named Part 5 typed by A5 - Create Part into System Context named Part 6 typed by A6
 * 
 * Expected Result:\ CESF2CESB
 *
 * - S1 should be transitioned - ComponentExchanges allocating functional exchanges should be created - An CES2CIS
 * should be possible and create a new diagram
 * 
 * 
 * - An CESF2CESB on S2 should ask 3 times the popup - S21 should conveys C_1 - S22 should conveys Exchange 1 - S23
 * should conveys Exchange 1 - S24 should conveys C_1 - S25 should conveys C_1
 * 
 */
public class CreateRule_ESF2ESB_01 extends TopDownTransitionTestCase {
  private String id_s1 = "79c04978-8d1c-4717-8b23-b640bf0400ad";
  private String id_ir11 = "33204ca9-72cc-43cb-b06c-aed0529a7657";
  private String id_ir12 = "287f6094-90be-4532-a32c-ef981d0a85d8";
  private String id_ir13 = "58a321a7-929f-4a95-a456-39eee2f5e2c0";
  private String id_sm11 = "16dc9062-236f-4f6d-adc0-126095264fc6";
  private String id_sm12 = "1c718dca-a798-4237-bf47-a4c1711833b7";
  private String id_sm13 = "390d9497-92ef-4d31-be7b-9e93947459c6";
  private String id_sm14 = "190ffc27-f044-4db4-a2e0-41373dda677c";
  private String id_s2 = "7ef57abc-64ee-464e-8482-ed1ae0e31b7d";
  private String id_ir21 = "ed2d7bdf-a552-4db6-a6a8-72ae0dd5e79b";
  private String id_ir22 = "435ab493-b271-40d8-88d5-c4b4f875c9bd";
  public static String id_sm21 = "71836adf-6851-4a0b-9758-91a957397e3a";
  public static String id_sm22 = "7171ad0a-46c9-4b93-9528-9c1682b13a0a";
  public static String id_sm23 = "8ae42d3a-dbf7-4bf9-8082-ae16376844c3";
  public static String id_sm24 = "5b6c19d8-76eb-478e-a553-10e5d343b5a0";
  public static String id_sm25 = "f69eddff-8341-44fe-b21a-878ba05cb53a";
  private String id_eso21 = "c23056b0-b68f-48fe-9fa7-157eb15d6990";
  private String id_ero22 = "62d35da0-5d8a-4da9-a056-e406ab446044";
  private String id_eso24 = "353ae9aa-3ffa-42de-8a56-b2143266aaaa";
  private String id_ero25 = "cabf080f-9da7-42ab-bfe8-64ebbce15bb0";
  private String id_eso26 = "ac1afc6f-bb43-460f-830d-a3db30e5278e";
  private String id_ero27 = "3994d680-db25-4b28-94bc-50844ab9b8d2";
  private String id_eso28 = "37caf126-581e-4f79-a8f5-d463abe085ee";
  private String id_ero29 = "dc75bb75-000e-4a8c-827c-6acaf9fc6ac9";
  private String id_eso210 = "021bca8e-4f18-4ca2-9d21-bf7ac456ac52";
  private String id_ero211 = "b0cac07f-d6e1-4bc5-868f-779a7f4be418";

  public static String id_exchange_1 = "a181a17e-d785-472b-8a6a-6e234d18668c";
  public static String id_c_1 = "6ed3147b-e5fb-414e-9447-5eaf5dc208e9";

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
    performESFtoESBTransition(Arrays.asList(getObject(id_s1)));
    Scenario ctxS01es = (Scenario) mustBeMonoTransitioned(id_s1);
    // Scenario s1t = (Scenario) s1ts.get(0);
    assertTrue("Should be a CES scenario", ctxS01es.getKind().equals(ScenarioKind.DATA_FLOW));

    mustBeTransitioned(id_ir11, ctxS01es);
    mustBeTransitioned(id_ir12, ctxS01es);
    mustBeTransitioned(id_ir13, ctxS01es);

    shouldBeAllocatingFunctionalExchange("FE3", id_sm11, ctxS01es);
    shouldBeAllocatingFunctionalExchange("FE1", id_sm12, ctxS01es);
    shouldBeAllocatingFunctionalExchange("FE1", id_sm13, ctxS01es);
    shouldBeAllocatingFunctionalExchange("FE3", id_sm14, ctxS01es);
  }

  private void shouldBeAllocatingFunctionalExchange(String fctExchName, String seqMsgId, EObject container) {
    SequenceMessage seqMessage = getObject(seqMsgId);
    String seqMsgName = (seqMessage instanceof AbstractNamedElement ? ((AbstractNamedElement) seqMessage).getName()
        : seqMessage.eClass().getName());
    FunctionalExchange funcExchg = (FunctionalExchange) seqMessage.getInvokedOperation();
    assertEquals(fctExchName, funcExchg.getName());
    SequenceMessage smFromFuncExchg = (SequenceMessage) mustBeTransitioned(seqMsgId, container);
    AbstractEventOperation invokedOper = smFromFuncExchg.getInvokedOperation();
    assertNotNull(NLS.bind(Messages.NullElement, seqMsgName), smFromFuncExchg.getInvokedOperation());
    assertTrue(Messages.ShouldBeInstanceof, invokedOper instanceof ComponentExchange);

    for (ComponentExchangeFunctionalExchangeAllocation alloc : funcExchg
        .getIncomingComponentExchangeFunctionalExchangeRealizations()) {
      if (alloc.getAllocatingComponentExchange().equals(invokedOper)) {
        return;
      }
    }
    assertTrue("Should link to allocating component exchange", false);

  }

  private void step2() {
    performEStoISTransition(Arrays.asList(getObject(id_s1)));
    List<EObject> s1ts = mustBeMultiTransitioned(id_s1, 2);
    Scenario s1t = (Scenario) s1ts.get(1);
    assertTrue("Should be a CIS scenario", s1t.getKind() == ScenarioKind.INTERFACE);

    mustBeTransitionedAndReference(id_ir11, s1t);
    mustBeTransitionedAndReference(id_ir12, s1t);
    mustBeTransitionedAndReference(id_ir13, s1t);
  }

  private void step3() {
    performESFtoESBTransition(Arrays.asList(getObject(id_s2)));
    List<EObject> s2ts = mustBeMultiTransitioned(id_s2, 1);
    Scenario s2t = (Scenario) s2ts.get(0);

    mustBeTransitioned(id_ir21, s2t);
    mustBeTransitioned(id_ir22, s2t);

    EObject c1 = getObject(id_c_1);
    assertNotNull(NLS.bind(Messages.NullElement, "c1"), c1);

    EObject e1 = getObject(id_exchange_1);
    assertNotNull(NLS.bind(Messages.NullElement, "e1"), e1);

    // C1
    shouldBeLinkedTo("eso21", id_eso21, s2t, c1);
    shouldBeLinkedTo("ero22", id_ero22, s2t, c1);

    // E1
    shouldBeLinkedTo("eso24", id_eso24, s2t, e1);
    shouldBeLinkedTo("eso25", id_ero25, s2t, e1);
    shouldBeLinkedTo("eso26", id_eso26, s2t, e1);
    shouldBeLinkedTo("eso27", id_ero27, s2t, e1);

    // C1
    shouldBeLinkedTo("eso28", id_eso28, s2t, c1);
    shouldBeLinkedTo("eso29", id_ero29, s2t, c1);
    shouldBeLinkedTo("eso210", id_eso210, s2t, c1);
    shouldBeLinkedTo("eso211", id_ero211, s2t, c1);
  }

  private void shouldBeLinkedTo(String string_p, String idE11_p, EObject container, EObject operation) {
    EObject res = mustBeTransitioned(idE11_p, container);
    EObject op = null;

    if (res instanceof EventSentOperation) {
      op = ((EventSentOperation) res).getOperation();
    }

    if (res instanceof EventReceiptOperation) {
      op = ((EventReceiptOperation) res).getOperation();
    }

    assertNotNull(NLS.bind("operation related to ''{0}'' is null", string_p), op);
    String opName = (op instanceof AbstractNamedElement ? ((AbstractNamedElement) op).getName()
        : op.eClass().getName());
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, string_p, opName), op.equals(operation));

  }

}
