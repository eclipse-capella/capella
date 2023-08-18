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
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test contextuality of Actor transition
 * 
 * <pre>
 * Model is created with the following elements�
 
  * - Rename InterfacePkg from System Analysis to Interfaces
 * - Create ExchangeItem into Interfaces named EI1 [FLOW]
 * - Create ExchangeItem into Interfaces named EI2 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI3 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI4 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI5 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI6 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI7 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI8 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI9 [OPERATION]
 * - Create ExchangeItem into Interfaces named EI10 [OPERATION]

 * - Create SystemFunction into System Functions named Root System Function
 * - Create FunctionRealization into Root System Function to Root Operational Activity
 * - Create SystemFunction into Root System Function named SF1
 * - Create FunctionOutputPort into SF1 named FOP11
 * - Create FunctionOutputPort into SF1 named FOP12
 * - Create SystemFunction into Root System Function named SF2
 * - Create FunctionInputPort into SF2 named FIP21
 * - Create SystemFunction into Root System Function named SF3
 * - Create FunctionInputPort into SF3 named FIP31
 * - Create SystemFunction into Root System Function named SF4
 * - Create FunctionInputPort into SF4 named FIP41
 * - Create SystemFunction into Root System Function named SF5
 * - Create FunctionOutputPort into SF5 named FOP51
 * - Create SystemFunction into Root System Function named SF6
 * - Create FunctionOutputPort into SF6 named FOP61
 * - Create SystemFunction into Root System Function named SF7
 * - Create FunctionInputPort into SF7 named FIP71
 * - Create FunctionOutputPort into SF7 named FOP71
 * - Create SystemFunction into Root System Function named SF8
 * - Create FunctionInputPort into SF8 named FIP81
 * - Create FunctionalExchange into Root System Function named FE1 from FOP11(SF1) to FIP21(SF2)
 *    -> Linked to ExchangeItem(EI2)
 *    -> Linked to ExchangeItem(EI1)
 * - Create FunctionalExchange into Root System Function named FE2 from FOP12(SF1) to FIP31(SF3)
 * - Create FunctionalExchange into Root System Function named FE3 from FOP51(SF5) to FIP41(SF4)
 * - Create FunctionalExchange into Root System Function named FE4 from FOP61(SF6) to FIP71(SF7)
 *    -> Linked to ExchangeItem(EI9)
 *   
 *   Perform interface transition of FunctionalExchange FE4
 *   
 *   -> Add Link on FE4 to ExchangeItem(EI8)
 *  
 * 
 * - Create FunctionalExchange into Root System Function named FE5 from FOP71(SF7) to FIP81(SF8)
 *    -> Linked to ExchangeItem(EI10)
 * - Rename System from System Analysis to System
 * - Create ComponentFunctionalAllocation into System to SF1
 * - Create ComponentFunctionalAllocation into System to SF5
 * - Create StateMachine into System named SM1
 * - Create Region into SM1 named R11
 * - Create ComponentPort into System named CP1
 * - Create PortRealization into CP1 to FOP11
 * - Create ComponentPort into System named CP2
 * - Create PortRealization into CP2 to FOP12
 * - Create PortRealization into CP2 to FOP51
 * - Rename ActorPkg from System Analysis to Actors
 * - Create Actor into Actors named A1
 * - Create ComponentFunctionalAllocation into A1 to SF2
 * - Create ComponentPort into A1 named CP11
 * - Create PortRealization into CP11 to FIP21
 * - Create Actor into Actors named A2
 * - Create ComponentFunctionalAllocation into A2 to SF3
 * - Create ComponentFunctionalAllocation into A2 to SF4
 * - Create ComponentPort into A2 named CP21
 * - Create PortRealization into CP21 to FIP31
 * - Create PortRealization into CP21 to FIP41
 * - Create Actor into Actors named A3
 * - Create ComponentFunctionalAllocation into A3 to SF6
 * - Create ComponentPort into A3 named CP31
 * - Create PortRealization into CP31 to FOP61
 * - Create Actor into Actors named A4
 * - Create ComponentFunctionalAllocation into A4 to SF7
 * - Create ComponentPort into A4 named CP41
 * - Create PortRealization into CP41 to FIP71
 * - Create Actor into Actors named A5
 * - Create ComponentFunctionalAllocation into A5 to SF8
 * - Rename SystemContext from System Analysis to System Context
 * - Create Connection into System Context named C1 from CP1(System) to CP11(A1)
 *    -> Linked to ExchangeItem(EI3)
 *    -> Linked to ExchangeItem(EI2)
 * - Create ConnectionFunctionalExchangeAllocation into C1 to FE1
 * - Create Connection into System Context named C2 from CP2(System) to CP21(A2)
 * - Create ConnectionFunctionalExchangeAllocation into C2 to FE2
 * - Create ConnectionFunctionalExchangeAllocation into C2 to FE3
 * - Create Connection into System Context named C3 from CP31(A3) to CP41(A4)
 * - Create ConnectionFunctionalExchangeAllocation into C3 to FE4
 * - Create Part into System Context named part_P1 typed by System
 * - Create Part into System Context named part_P2 typed by A1
 * - Create Part into System Context named part_P3 typed by A2
 * - Create Part into System Context named part_P4 typed by A3
 * - Create Part into System Context named part_P5 typed by A4
 * - Create Part into System Context named part_P6 typed by A5
 * - Create FunctionalExchange into Root System Function named FE6 from FunctionOutputPort 2(SF7) to FunctionInputPort 2(SF8)

 * Expected Result:\
 * - Generation on _id_system should create interface for C1 with 3 allocations (EI on C1+EI on allocated FEs) ,
 * and interface for C2 with 2 allocations (EI on C1+EI on allocated FEs)

 * - Generation on _id_a3 should create interface for C3 with 2 allocation, and update interface of FE4 with 2 allocations
 * - Generation on _id_a5 should not create interface for FE6 , and create interface of FE5 with 1 allocation
 * 
 * </pre>
 * 
 */
public class GenerateInterface01_01 extends TopDownTransitionTestCase {
  private String id_fe1 = "fbe521a5-8b9f-4cd1-acf4-4908eaf21f39";
  private String id_fe2 = "694c3acb-e4f8-41df-991a-ca6b35061072";
  private String id_fe4 = "b0f1b3d1-44ed-4fb1-a141-045503873eaa";
  private String id_fe5 = "723fc6fa-2da5-47ec-be27-42d9f90dd25d";
  private String id_ei1 = "655b7719-4b3b-4ff6-88dc-4f16e9aed3ed";
  private String id_ei2 = "8c05d185-f2cf-4dbd-b687-4b56ba53f94e";
  private String id_ei3 = "9bf80cbc-d15f-4e43-9859-4e8c75b9126c";
  private String id_ei4 = "35bef740-04a9-4e75-90e2-e3816ad22bef";
  private String id_ei5 = "33327804-ade6-4c8c-ace1-29ede2feb365";
  private String id_ei8 = "4e4ce8c1-f1d5-4619-b52b-cfe771487cf7";
  private String id_ei9 = "be6ed420-a271-4932-bc72-88539905b9ca";
  private String id_system = "c8f8f0ca-00c1-470d-8b39-a2298b857614";
  private String id_a3 = "41b76f82-6cc4-44c2-bd1b-8251cb69b656";
  private String id_a5 = "4e63136d-c3a4-4251-a398-f3bda0f39686";
  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Generation_Interface");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
  }

  private void step1() {
    performGenerateInterfacesCommand(Arrays.asList(getObject(id_system)));
    Interface itf_c1 = (Interface) mustBeTransitionedTo(id_fe1, CsPackage.Literals.INTERFACE);
    ExchangeItem ei1 = (ExchangeItem) shouldExist(id_ei1);
    ExchangeItem ei2 = (ExchangeItem) shouldExist(id_ei2);
    ExchangeItem ei3 = (ExchangeItem) shouldExist(id_ei3);
    shouldAllocate(itf_c1, ei1);
    shouldAllocate(itf_c1, ei2);
    shouldAllocate(itf_c1, ei3);

    // Only 3 allocations should exists
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo,
        CsPackage.Literals.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS.getName(), "3"),
        itf_c1.getOwnedExchangeItemAllocations().size() == 3);

    Interface itf_c2 = (Interface) mustBeTransitionedTo(id_fe2, CsPackage.Literals.INTERFACE);
    ExchangeItem ei4 = (ExchangeItem) shouldExist(id_ei4);
    ExchangeItem ei5 = (ExchangeItem) shouldExist(id_ei5);
    shouldAllocate(itf_c2, ei4);
    shouldAllocate(itf_c2, ei5);

    // Only 2 allocations should exists
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo,
        CsPackage.Literals.INTERFACE__OWNED_EXCHANGE_ITEM_ALLOCATIONS.getName(), "2"),
        itf_c2.getOwnedExchangeItemAllocations().size() == 2);

  }

  private void step2() {
    performGenerateInterfacesCommand(Arrays.asList(getObject(id_a3)));
    Interface itf_c3 = (Interface) mustBeTransitionedTo(id_fe4, CsPackage.Literals.INTERFACE);
    ExchangeItem ei9 = (ExchangeItem) shouldExist(id_ei9);
    ExchangeItem ei8 = (ExchangeItem) shouldExist(id_ei8);

    Interface itf_fe4 = (Interface) mustBeTransitionedTo(id_fe4, CsPackage.Literals.INTERFACE);

    shouldAllocate(itf_c3, ei9);
    shouldAllocate(itf_c3, ei8);
    shouldAllocate(itf_fe4, ei9);
    shouldAllocate(itf_fe4, ei8);
  }

  private void step3() {
    performGenerateInterfacesCommand(Arrays.asList(getObject(id_a5)));
    // When there is no component port, no interface is generated
    shouldNotBeTransitioned(id_fe5, CsPackage.Literals.INTERFACE);
  }

}
