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
 *
 * Perform OES to ES (System) transition, check that the referenced scenarios were also transitioned; If we have an
 * invalid referenced scenario, it is not transitioned (ex OES references OAS)
 * 
 * * Expected Result: The referenced scenarios are also transitioned and after transition, the transitioned scenario
 * reference a valid scenario (at same level)
 * 
 */
public class ReferencedScenarios_01 extends TopDownTransitionTestCase {
  public static final String OES_OPERATIONALCAPABILITY_1 = "55ac5c1e-0d95-4fb0-98d7-8aa64ca18d57"; //$NON-NLS-1$
  public static final String OES_OPERATIONALCAPABILITY_1_A = "abbda34d-e670-4d31-a0ae-b2ffb713566f"; //$NON-NLS-1$
  public static final String OES_OPERATIONALCAPABILITY_2 = "14bb527d-0c12-4b1f-9798-3163c6189613"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_2 = "d257c3dc-b4f2-4e7c-91f3-61c465e53a21"; //$NON-NLS-1$
  public static final String OAS_OPERATIONALCAPABILITY_4 = "5f7bfff5-c03d-4292-864b-4feeaa9a2d78"; //$NON-NLS-1$
  public static final String OES_OPERATIONALCAPABILITY_4 = "483fd0b3-f52e-4de4-b8d1-109d44649d59"; //$NON-NLS-1$
  
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_1 = "20076cac-66b1-491e-abef-e2325f2d3b66"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_1_A = "52e7a7f5-553b-48f1-88fd-5290bb572b01"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_2 = "0c1ada3b-b6ec-45f8-b6bd-c5110e352a54"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_2_1 = "fed23a79-5aa4-4dec-8bd5-f36833599e1f"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_OES_OPERATIONALCAPABILITY_4 = "fceda0ba-dda1-4664-8927-7ce6884d5e79"; //$NON-NLS-1$

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
  }

  /*
   * OES to ES (System)
   */
  private void step1() {
    performEStoESTransition(Arrays.asList(getObject(OES_OPERATIONALCAPABILITY_1)));

    checksStep1();
  }

  private void checksStep1() {
    // test circular reference between OES_OPERATIONALCAPABILITY_1 and OES_OPERATIONALCAPABILITY_1_A
    // for OES_OPERATIONALCAPABILITY_1 it has a reference to OES_OPERATIONALCAPABILITY_1_A and
    // OES_OPERATIONALCAPABILITY_1_A has a reference to OES_OPERATIONALCAPABILITY_1
    Scenario scenario1 = mustBeTransitioned(OES_OPERATIONALCAPABILITY_1);
    Scenario scenario2 = mustBeTransitioned(OES_OPERATIONALCAPABILITY_1_A);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OES_OPERATIONALCAPABILITY_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OES_OPERATIONALCAPABILITY_1_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario1));
  }

  /*
   * OES to ES (System), when an invalid scenario is referenced
   */
  private void step2() {
    performEStoESTransition(Arrays.asList(getObject(OES_OPERATIONALCAPABILITY_2)));

    checksStep2();
  }

  private void checksStep2() {
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

  /*
   * OES to ES (System), multiselect
   */
  private void step3() {
    performEStoESTransition(
        Arrays.asList(getObject(OES_OPERATIONALCAPABILITY_1), getObject(OES_OPERATIONALCAPABILITY_2)));

    checksStep1();
  }

  /*
   * OAS to FS (System), when an invalid scenario of OES kind is referenced
   */
  private void step4() {
    performFStoFSTransition(Arrays.asList(getObject(OAS_OPERATIONALCAPABILITY_4)));

    // test that an invalid referenced scenario (OAS_OPERATIONALCAPABILITY_4) is not transitioned
    Scenario scenario1 = mustBeTransitioned(OAS_OPERATIONALCAPABILITY_4);
    shouldNotBeTransitioned(OES_OPERATIONALCAPABILITY_4);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_OES_OPERATIONALCAPABILITY_4,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);

    assertTrue(reference1.getReferencedScenario() == null);
  }
}
