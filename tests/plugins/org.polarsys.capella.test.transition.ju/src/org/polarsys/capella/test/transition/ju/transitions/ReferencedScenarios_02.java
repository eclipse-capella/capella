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
 * Perform OAS to FS (System) transitions, check that the referenced scenarios were also transitioned; If we have an
 * invalid referenced scenario, it is not transitioned (ex OAS references OES). Perform also OAS to OES.
 * 
 * * Expected Result: The referenced scenarios are also transitioned and after transition, the transitioned scenario
 * reference a valid scenario (at same level)
 * 
 */
public class ReferencedScenarios_02 extends TopDownTransitionTestCase {
  public static final String OAS_OPERATIONALCAPABILITY_3 = "b02a5f31-328b-4639-828e-a156aaeb2adf"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_3_A = "2341b930-cc46-43d3-84c5-fb87723b6b3f"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_3_B = "9eea1eab-2855-4825-8ddf-5e85013ca5b0"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_4 = "5f7bfff5-c03d-4292-864b-4feeaa9a2d78"; //$NON-NLS-1$
  public static final String OES_OPERATIONALCAPABILITY_4 = "483fd0b3-f52e-4de4-b8d1-109d44649d59"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_5 = "faac6c0e-c4b6-4340-adea-c80991633cfc"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_5_A = "f97bf3a9-13a5-4928-9f55-64769926721d"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_5_B = "6f3cc51c-ae96-43db-8b8e-046d5492f915"; //$NON-NLS-1$
  public static final String OES_OPERATIONALCAPABILITY_1 = "55ac5c1e-0d95-4fb0-98d7-8aa64ca18d57"; //$NON-NLS-1$
  public static final String OES_OPERATIONALCAPABILITY_2 = "14bb527d-0c12-4b1f-9798-3163c6189613"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_2 = "d257c3dc-b4f2-4e7c-91f3-61c465e53a21"; //$NON-NLS-1$

  public static final String INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_3 = "5b9533fa-c5ae-4a9e-9ab7-a2c9fa0f4e59"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_3_A = "5f423544-91d6-4438-b61c-39bd44c97fef"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_3_B = "72492824-1733-4545-961b-463a86f5b692"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_3_B_1 = "518cb0f7-f0df-4bd8-8368-4eb1d0167fac"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_4 = "fceda0ba-dda1-4664-8927-7ce6884d5e79"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_5 = "fbe7a08a-ea8c-4be9-9ba6-2655b7e04661"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_5_A = "d9ffa66d-8b1d-4353-84be-77f90ebb3691"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_5_B = "85ce5f6f-99b0-43fd-ad01-3228a7101032"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_5_B_1 = "9a28c3e0-410c-4077-bbd0-4ddb5f256a6b"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_1 = "20076cac-66b1-491e-abef-e2325f2d3b66"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_1_A = "52e7a7f5-553b-48f1-88fd-5290bb572b01"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_2 = "0c1ada3b-b6ec-45f8-b6bd-c5110e352a54"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_2_1 = "fed23a79-5aa4-4dec-8bd5-f36833599e1f"; //$NON-NLS-1$

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
   * OAS to FS (System)
   */
  private void step1() {
    performFStoFSTransition(Arrays.asList(getObject(OAS_OPERATIONALCAPABILITY_3)));

    checksStep1();
  }

  private void checksStep1() {
    // test circular reference between OES_OPERATIONALCAPABILITY_1 and OES_OPERATIONALCAPABILITY_1_A
    // for OES_OPERATIONALCAPABILITY_1 it has a reference to OES_OPERATIONALCAPABILITY_1_A and
    // OES_OPERATIONALCAPABILITY_1_A has a reference to OES_OPERATIONALCAPABILITY_1
    Scenario scenario1 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_3);
    Scenario scenario2 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_3_A);
    Scenario scenario3 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_3_B);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_3,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_3_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario3));

    InteractionUse reference3 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_3_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario3);
    assertTrue(reference3.getReferencedScenario().equals(scenario2));

    InteractionUse reference4 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_3_B_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario3);
    assertTrue(reference4.getReferencedScenario().equals(scenario1));
  }

  /*
   * OAS to FS (System), when an invalid scenario is referenced
   */
  private void step2() {
    performFStoFSTransition(Arrays.asList(getObject(OAS_OPERATIONALCAPABILITY_4)));

    // test that an invalid referenced scenario (OAS_OPERATIONALCAPABILITY_4) is not transitioned
    Scenario scenario1 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_4);
    shouldNotBeTransitioned(OES_OPERATIONALCAPABILITY_4);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OES_OPERATIONALCAPABILITY_4,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);

    assertTrue(reference1.getReferencedScenario() == null);
  }

  /*
   * OAS to OES
   */
  private void step3() {
    performFStoESTransition(Arrays.asList(getObject(OAS_OPERATIONALCAPABILITY_5)));

    // test that an invalid referenced scenario (OAS_OPERATIONALCAPABILITY_2) is not transitioned
    Scenario scenario1 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_5);
    Scenario scenario2 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_5_A);
    Scenario scenario3 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_5_B);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_5,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_5_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario3));

    InteractionUse reference3 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_5_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario3);
    assertTrue(reference3.getReferencedScenario().equals(scenario1));

    InteractionUse reference4 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OAS_OPERATIONALCAPABILITY_5_B_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario3);
    assertTrue(reference4.getReferencedScenario().equals(scenario2));
  }

  /*
   * OAS to FS (System), multi-select
   */
  private void step4() {
    performFStoFSTransition(
        Arrays.asList(getObject(OAS_OPERATIONALCAPABILITY_4), getObject(OAS_OPERATIONALCAPABILITY_3)));

    Scenario scenario1 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_4);
    shouldNotBeTransitioned(OES_OPERATIONALCAPABILITY_4);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OES_OPERATIONALCAPABILITY_4,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);

    assertTrue(reference1.getReferencedScenario() == null);

    // checks for OAS_OPERATIONALCAPABILITY_3
    checksStep1();
  }
  
  /*
   * OES to ES (System), when an invalid scenario is referenced
   */
  private void step5() {
    performEStoESTransition(Arrays.asList(getObject(OES_OPERATIONALCAPABILITY_2)));
    // test that an invalid referenced scenario (OAS_OPERATIONALCAPABILITY_2) is not transitioned
    Scenario scenario1 = mustBeTransitioned(OES_OPERATIONALCAPABILITY_2);
    Scenario scenario2 = mustBeTransitioned(OES_OPERATIONALCAPABILITY_1);
    Scenario scenario3 = shouldNotBeTransitioned(OAS_OPERATIONALCAPABILITY_2);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OES_OPERATIONALCAPABILITY_2,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference3 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OES_OPERATIONALCAPABILITY_2_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference3.getReferencedScenario() == null);
  }
}
