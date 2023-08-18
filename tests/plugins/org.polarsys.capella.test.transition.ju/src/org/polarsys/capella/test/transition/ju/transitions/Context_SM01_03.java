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
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 * - Rename LogicalComponent from Logical Architecture to Logical System
 * - Create StateMachine into Logical System named Logical System State Machine
 * - Create Region into Logical System State Machine named Default Region
 * - Create SystemRealization into Logical System to System
 * 
 * 
 * Expected Result:\
 * - Performing LCPC transition on LogicalSystem should transition SM
 * 
 * </pre>
 */
public class Context_SM01_03 extends TopDownTransitionTestCase {

  private String id_RootSF = "117be734-c397-431f-8f85-d05801bfe0d3";

  private String id_SF1 = "92574d6e-1b96-4807-9b56-35f5aac2aeb6";
  private String id_SF2 = "ecfb8427-ea46-4d0e-9937-e3872dd9e123";
  private String id_SF3 = "0f2bb4df-81bf-4472-aa7a-13c40d69cea9";
  private String id_FE_SF1_SF2 = "de7933db-a0fc-446f-b1c8-81a8917b7228";
  
  private String id_SM1 = "02e279c5-ab9d-4802-a7ce-9a860e758a22";
  private String id_R11 = "dd19a302-cbc8-4bed-bd9a-4141e23adf91";
  private String id_M111 = "4e32329b-7f36-4710-8e3d-127a5355cb2e";
  private String id_M112 = "0dc7b156-c0e2-47e5-8c20-c598057cd5ef";
  private String id_M113 = "cb28b36a-183c-4bce-8575-d08774305a19";
  private String id_ST112 = "cd74df8f-4436-4d17-91f3-507b873a328e";
  private String id_ST113 = "4e580161-0c27-4393-91e6-38f709c5a0e5";

  private String id_SM11 = "dbe01bad-2677-425e-9c92-d4ade5ce3983";
  private String id_M1111 = "f8d09150-ac07-4732-b36b-f8032e5d8bb4";
  private String id_M1112 = "9b267f7b-e54d-46f3-96d0-d1bf0eb2e368";
  private String id_M1113 = "5cbae8f2-a2b5-424f-8be0-cdbb980ba984";
  private String id_ST1112 = "6ab7813f-d7f1-4237-be2e-e916b2650ade";
  private String id_ST1113 = "3e13f454-a996-4911-894c-9bec5321e2dd";

  private String id_e1 = "ce4cdd11-ccdb-47f9-ba8e-5be4ccef0518";
  private String id_e2 = "ae3a2433-90a7-4f00-9a27-7bfcb28ceac6";
  private String id_e3 = "695ba580-08d3-4871-884d-de666544377a";

  private String id_data = "f7fd2415-a37d-47aa-8e9e-f8ba9b59c644";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_I01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
    step4();
    step5();
    step6();
    step7();
  }

  private void step1() {
    performStateMachineTransition(Arrays.asList(getObject(id_SM1)));
    shouldNotBeTransitioned(id_SM11);

    // test ISTATE__REFERENCED_STATES
    mustBeTransitioned(id_SM1);
    mustBeTransitioned(id_R11);
    EObject M111 = getObject(id_M111);
    EObject M111t = mustBeTransitioned(id_M111);
    EObject M112t = mustBeTransitioned(id_M112);

    EObject M1111 = getObject(id_M1111);
    EObject M1112 = getObject(id_M1112);
    shouldNotBeTransitioned(id_M1111);
    shouldNotBeTransitioned(id_M1112);

    mustBeTransitioned(id_M113);
    EObject ST112 = getObject(id_ST112);
    EObject ST112t = mustBeTransitioned(id_ST112);
    EObject ST113 = getObject(id_ST113);
    EObject ST113t = mustBeTransitioned(id_ST113);

    mustBeLinkedTo(M111t, M111t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustBeLinkedTo(M111t, M112t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustNotBeLinkedTo(M111t, M1111, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustNotBeLinkedTo(M111t, M1112, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);

    // test ABSTRACT_REGION__INVOLVED_STATES
    EObject R11t = mustBeTransitioned(id_R11);
    mustBeLinkedTo(R11t, M111t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustBeLinkedTo(R11t, M112t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustNotBeLinkedTo(R11t, M1111, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustNotBeLinkedTo(R11t, M1112, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);

    // test Trigger / effect
    EObject SF1 = getObject(id_SF1);
    EObject SF2 = getObject(id_SF2);
    EObject FE_SF1_SF2 = getObject(id_FE_SF1_SF2);
    EObject SF1t = mustBeTransitioned(id_SF1);
    EObject SF2t = mustBeTransitioned(id_SF2);
    EObject FE_SF1_SF2t = mustBeTransitioned(id_FE_SF1_SF2);
    mustBeLinkedTo(ST112, SF1, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustBeLinkedTo(ST112, SF2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustBeLinkedTo(ST112, FE_SF1_SF2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustNotBeLinkedTo(ST112t, SF1, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustNotBeLinkedTo(ST112t, SF2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustNotBeLinkedTo(ST112t, FE_SF1_SF2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustBeLinkedTo(ST112t, SF1t, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustBeLinkedTo(ST112t, SF2t, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustBeLinkedTo(ST112t, FE_SF1_SF2t, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);

    EObject E1 = getObject(id_e1);
    EObject E2 = getObject(id_e2);
    mustBeLinkedTo(ST113, E1, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustBeLinkedTo(ST113, E2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustBeLinkedTo(ST113t, E1, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustBeLinkedTo(ST113t, E2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    
    // test Do Activity / Entry / Exit / Available functions
    EObject SF3 = getObject(id_SF3);
    EObject SF3t = mustBeTransitioned(id_SF3);
    mustBeLinkedTo(M111, SF3, CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
    mustBeLinkedTo(M111, SF3, CapellacommonPackage.Literals.STATE__ENTRY);
    mustBeLinkedTo(M111, SF3, CapellacommonPackage.Literals.STATE__EXIT);
    mustBeLinkedTo(M111, SF3, CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_FUNCTIONS);
    mustNotBeLinkedTo(M111t, SF3, CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
    mustNotBeLinkedTo(M111t, SF3, CapellacommonPackage.Literals.STATE__ENTRY);
    mustNotBeLinkedTo(M111t, SF3, CapellacommonPackage.Literals.STATE__EXIT);
    mustNotBeLinkedTo(M111t, SF3, CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_FUNCTIONS);
    mustBeLinkedTo(M111t, SF3t, CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
    mustBeLinkedTo(M111t, SF3t, CapellacommonPackage.Literals.STATE__ENTRY);
    mustBeLinkedTo(M111t, SF3t, CapellacommonPackage.Literals.STATE__EXIT);
    mustBeLinkedTo(M111t, SF3t, CapellacommonPackage.Literals.STATE__AVAILABLE_ABSTRACT_FUNCTIONS);
    
    EObject E3 = getObject(id_e3);
    mustBeLinkedTo(M111, E3, CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
    mustBeLinkedTo(M111, E3, CapellacommonPackage.Literals.STATE__ENTRY);
    mustBeLinkedTo(M111, E3, CapellacommonPackage.Literals.STATE__EXIT);
    mustBeLinkedTo(M111t, E3, CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
    mustBeLinkedTo(M111t, E3, CapellacommonPackage.Literals.STATE__ENTRY);
    mustBeLinkedTo(M111t, E3, CapellacommonPackage.Literals.STATE__EXIT);
  }

  private void step2() {
    performStateMachineTransition(Arrays.asList(getObject(id_SM11)));
    mustBeTransitioned(id_SM11);

    // test ISTATE__REFERENCED_STATES
    EObject M1111 = getObject(id_M1111);
    EObject M1112 = getObject(id_M1112);
    EObject M1111t = mustBeTransitioned(id_M1111);
    EObject M1112t = mustBeTransitioned(id_M1112);
    mustBeTransitioned(id_M1113);
    mustBeTransitioned(id_ST1112);
    mustBeTransitioned(id_ST1113);

    EObject M111t = mustBeTransitioned(id_M111);
    EObject M112t = mustBeTransitioned(id_M112);
    mustBeLinkedTo(M111t, M111t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustBeLinkedTo(M111t, M112t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustNotBeLinkedTo(M111t, M1111t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustNotBeLinkedTo(M111t, M1112t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustNotBeLinkedTo(M111t, M1111, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustNotBeLinkedTo(M111t, M1112, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);

    EObject R11t = mustBeTransitioned(id_R11);
    mustBeLinkedTo(R11t, M111t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustBeLinkedTo(R11t, M112t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustNotBeLinkedTo(R11t, M1111t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustNotBeLinkedTo(R11t, M1112t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
  }

  private void step3() {
    performStateMachineTransition(Arrays.asList(getObject(id_SM1)));
    mustBeTransitioned(id_SM11);

    // test ISTATE__REFERENCED_STATES
    EObject M1111t = mustBeTransitioned(id_M1111);
    EObject M1112t = mustBeTransitioned(id_M1112);
    mustBeTransitioned(id_M1113);
    mustBeTransitioned(id_ST1112);
    mustBeTransitioned(id_ST1113);

    EObject M111t = mustBeTransitioned(id_M111);
    EObject M112t = mustBeTransitioned(id_M112);
    mustBeLinkedTo(M111t, M111t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustBeLinkedTo(M111t, M112t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustBeLinkedTo(M111t, M1111t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);
    mustBeLinkedTo(M111t, M1112t, ModellingcorePackage.Literals.ISTATE__REFERENCED_STATES);

    EObject R11t = mustBeTransitioned(id_R11);
    mustBeLinkedTo(R11t, M111t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustBeLinkedTo(R11t, M112t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustBeLinkedTo(R11t, M1111t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
    mustBeLinkedTo(R11t, M1112t, CapellacommonPackage.Literals.REGION__INVOLVED_STATES);
  }

  private void step4() {
    performFunctionalTransition(getObjects(id_RootSF));
    EObject SF1 = getObject(id_SF1);
    EObject SF2 = getObject(id_SF2);
    EObject SF1t = mustBeTransitioned(id_SF1);
    EObject SF2t = mustBeTransitioned(id_SF2);

    EObject ST112 = getObject(id_ST112);
    EObject ST112t = mustBeTransitioned(id_ST112);
    mustNotBeLinkedTo(ST112, SF2t, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustNotBeLinkedTo(ST112, SF1t, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);

    mustNotBeLinkedTo(ST112t, SF2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustNotBeLinkedTo(ST112t, SF1, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustBeLinkedTo(ST112t, SF2t, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustBeLinkedTo(ST112t, SF1t, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
  }

  private void step5() {
    performStateMachineTransition(Arrays.asList(getObject(id_SM1)));
    mustBeTransitioned(id_SM11);

    EObject SF1 = getObject(id_SF1);
    EObject SF2 = getObject(id_SF2);
    EObject SF1t = mustBeTransitioned(id_SF1);
    EObject SF2t = mustBeTransitioned(id_SF2);

    EObject ST112t = mustBeTransitioned(id_ST112);
    mustNotBeLinkedTo(ST112t, SF1, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustNotBeLinkedTo(ST112t, SF2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustBeLinkedTo(ST112t, SF1t, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustBeLinkedTo(ST112t, SF2t, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
  }

  private void step6() {
    performExchangeItemTransition(getObjects(id_data));
    EObject E1 = getObject(id_e1);
    EObject E2 = getObject(id_e2);
    EObject E1t = mustBeTransitioned(id_e1);
    EObject E2t = mustBeTransitioned(id_e2);

    EObject ST113 = getObject(id_ST113);
    EObject ST113t = mustBeTransitioned(id_ST113);
    mustNotBeLinkedTo(ST113, E1t, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustNotBeLinkedTo(ST113, E2t, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);

    mustBeLinkedTo(ST113t, E1, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustBeLinkedTo(ST113t, E2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustNotBeLinkedTo(ST113t, E1t, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustNotBeLinkedTo(ST113t, E2t, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
  }

  private void step7() {
    performStateMachineTransition(Arrays.asList(getObject(id_SM1)));
    mustBeTransitioned(id_SM11);

    EObject E1 = getObject(id_e1);
    EObject E2 = getObject(id_e2);
    EObject E1t = mustBeTransitioned(id_e1);
    EObject E2t = mustBeTransitioned(id_e2);

    EObject ST113t = mustBeTransitioned(id_ST113);
    mustNotBeLinkedTo(ST113t, E1, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustNotBeLinkedTo(ST113t, E2, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
    mustBeLinkedTo(ST113t, E1t, CapellacommonPackage.Literals.STATE_TRANSITION__EFFECT);
    mustBeLinkedTo(ST113t, E2t, CapellacommonPackage.Literals.STATE_TRANSITION__TRIGGERS);
  }
}