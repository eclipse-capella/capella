/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test that referenced scenarios are transitioned
 * 
 * Perform ES to ES transition, check that the referenced scenarios were also transitioned;
 * 
 * * Expected Result: The referenced scenarios are also transitioned and after transition, the transitioned scenario
 * reference a valid scenario (at same level)
 * 
 */
public class ReferencedScenarios_05 extends TopDownTransitionTestCase {
  public static final String ES_CAPABILITY_3 = "2913ab9d-dcc1-4278-88d5-6c7d6098c7b5"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_3_A = "6be6e3cf-a35f-4e4f-95ab-ae78b4622245"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_3_B = "421763bd-cba6-4fc8-8a10-cfe1e388e3f1"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_3_C = "44960693-9dfd-40d5-a407-b776d733a6ff"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_1_PKG = "ee0d3f96-74d8-4106-b039-34ec18e9188b"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_1_PKG2 = "4d7e32a3-2dbe-417d-bbeb-fab94525962a"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_1_PKG3_1 = "94dddddc-c0e4-4dc4-9cbf-7bf5fb8d4ee3"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_4 = "8cd7efd6-7e97-44ef-9f99-b071ea1bf972"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_4_A = "86bc37e6-307e-49d4-86ec-d3788b28c36c"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_5 = "b02b7c13-f3d4-4baf-a7f8-f607131636c4"; //$NON-NLS-1$
  public static final String ES_CAPABILITY_5_A = "4153274d-1dc3-4b74-94b1-a0ea874744d8"; //$NON-NLS-1$

  public static final String CAPABILITY_PKG3 = "e87d6866-2116-412b-b8d9-0d7dfb6fcc26";

  public static final String INTERACTIONUSE_ES_CAPABILITY_3 = "a0b7e155-f62f-4da7-a1ed-453ba8a66c2a"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_3_A = "ae298fa6-2411-4a24-bf6f-0644c324471b"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_3_B = "1ec37471-13d1-4d9d-baf4-e99095228997"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_1_PKG = "8e371bde-4204-41b7-a1cc-f2bc5c99de07"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_1_PKG3_1 = "f77cbcf4-fded-46c3-9876-99d606623276"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_1_PKG3_1_A = "47da29bf-c81a-42fa-9d49-620e0c517890"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_4 = "3ab74126-3ac2-405f-82fa-fb70aae4e12e"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_4_A = "f785cf32-7638-48a1-9519-c0c463fe8c66"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_5 = "7646ccaa-ee15-4b41-9f76-be3bd5623a04"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_ES_CAPABILITY_5_A = "9bf2ace7-a670-40b6-8374-a50229d5afbb"; //$NON-NLS-1$

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
    step4();
    step5();
  }

  /*
   * ES to ES (System to Logical), simple-select
   */
  private void step1() {
    performEStoESTransition(Arrays.asList(getObject(ES_CAPABILITY_3_B)));

    Scenario scenario1 = mustBeTransitioned(ES_CAPABILITY_3_B);
    Scenario scenario2 = mustBeTransitioned(ES_CAPABILITY_3_C);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_3_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));
  }

  /*
   * ES to ES (System to Logical), simple-select, transition again, check that the references are not broken
   */
  private void step2() {
    performEStoESTransition(Arrays.asList(getObject(ES_CAPABILITY_3_B)));

    Scenario scenario1 = mustBeTransitioned(ES_CAPABILITY_3_B);
    Scenario scenario2 = mustBeTransitioned(ES_CAPABILITY_3_C);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_3_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));
  }

  /*
   * ES to ES (System to Logical), multi-select
   */
  private void step3() {
    performEStoESTransition(
        Arrays.asList(getObject(ES_CAPABILITY_3), getObject(ES_CAPABILITY_1_PKG), getObject(CAPABILITY_PKG3)));

    Scenario scenario1 = mustBeTransitioned(ES_CAPABILITY_3);
    Scenario scenario2 = mustBeTransitioned(ES_CAPABILITY_3_A);
    Scenario scenario3 = mustBeTransitioned(ES_CAPABILITY_1_PKG);
    Scenario scenario4 = mustBeTransitioned(ES_CAPABILITY_1_PKG2);
    Scenario scenario5 = mustBeTransitioned(ES_CAPABILITY_1_PKG3_1);
    Scenario scenario6 = mustBeTransitioned(ES_CAPABILITY_3_B);
    Scenario scenario7 = mustBeTransitioned(ES_CAPABILITY_3_C);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_3,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_3_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario3));

    InteractionUse reference3 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_1_PKG,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference3.getReferencedScenario().equals(scenario4));

    InteractionUse reference4 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_1_PKG3_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario4);
    assertTrue(reference4.getReferencedScenario().equals(scenario7));

    InteractionUse reference5 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_1_PKG3_1_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario4);
    assertTrue(reference5.getReferencedScenario().equals(scenario6));

    InteractionUse reference7 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_3_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario6);
    assertTrue(reference7.getReferencedScenario().equals(scenario7));
  }

  /*
   * ES (functional) to ES (behavioral)
   */
  private void step4() {
    performESFtoESBTransition(Arrays.asList(getObject(ES_CAPABILITY_4)));

    Scenario scenario1 = mustBeTransitioned(ES_CAPABILITY_4);
    Scenario scenario2 = mustBeTransitioned(ES_CAPABILITY_4_A);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_4,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_4_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario1));
  }

  /*
   * ES to IS
   */
  private void step5() {
    performEStoISTransition(Arrays.asList(getObject(ES_CAPABILITY_5)));

    Scenario scenario1 = mustBeTransitioned(ES_CAPABILITY_5);
    Scenario scenario2 = mustBeTransitioned(ES_CAPABILITY_5_A);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_5,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_ES_CAPABILITY_5_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario1));
  }
}
