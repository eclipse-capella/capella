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
 * Perform FS to FS transition, check that the referenced scenarios were also transitioned;
 * 
 * * Expected Result: The referenced scenarios are also transitioned and after transition, the transitioned scenario
 * reference a valid scenario (at same level)
 * 
 */
public class ReferencedScenarios_04 extends TopDownTransitionTestCase {
  public static final String FS_CAPABILITY_3 = "f0f4030a-4da7-4418-8f3f-9a1b2564953f"; //$NON-NLS-1$
  public static final String FS_CAPABILITY_3_A = "458667fb-0c70-41e8-9790-6730afa0ec5d"; //$NON-NLS-1$
  public static final String FS_CAPABILITY_3_B = "1f52c7a0-f94e-4187-94a5-3a36658053c0"; //$NON-NLS-1$
  public static final String FS_CAPABILITY_3_C = "21c7ee84-fe5d-4ccc-9808-51e53a1f9dd7"; //$NON-NLS-1$
  public static final String FS_CAPABILITY_1_PKG = "c4f7e77a-5f06-4636-8543-77a625eb4c62"; //$NON-NLS-1$
  public static final String FS_CAPABILITY_1_PKG2 = "47602e17-cc4b-447d-a46d-6229776d9c1f"; //$NON-NLS-1$
  public static final String FS_CAPABILITY_1_PKG3_1 = "ce60497b-c5fc-4d6a-97b1-96f311699b77"; //$NON-NLS-1$
  public static final String FS_CAPABILITY_4 = "54c3ccfe-a8b4-4584-b51a-34ee200008d4"; //$NON-NLS-1$
  public static final String FS_CAPABILITY_4_A = "74d4791b-fc26-4505-b977-023ab5f78efb"; //$NON-NLS-1$

  public static final String CAPABILITY_PKG3 = "e87d6866-2116-412b-b8d9-0d7dfb6fcc26";

  public static final String INTERACTIONUSE_FS_CAPABILITY_3 = "5fe5d673-d33b-4f72-9431-930168374825"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_FS_CAPABILITY_3_A = "51c5b873-6866-4c0c-a556-4eaf2c5175ca"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_FS_CAPABILITY_3_B = "e430180b-aa07-427f-ae2b-d16bedbda7a4"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_FS_CAPABILITY_1_PKG = "bf727508-f31c-4e3c-b4dd-a06acb762fdb"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_FS_CAPABILITY_1_PKG3_1 = "7c874970-b5ed-4460-85f7-e08a7780d7bc"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_FS_CAPABILITY_1_PKG3_1_A = "68c53685-9c23-45e9-83aa-2b406ab12068"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_FS_CAPABILITY_4 = "d56de654-ab9c-4ea4-b157-5e0fa18c598a"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_FS_CAPABILITY_4_A = "942b35bd-82d5-41c0-bf27-7054c599df5f"; //$NON-NLS-1$

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
   * FS to FS (System to Logical), simple-select
   */
  private void step1() {
    performFStoFSTransition(Arrays.asList(getObject(FS_CAPABILITY_3_B)));

    Scenario scenario1 = mustBeTransitioned(FS_CAPABILITY_3_B);
    Scenario scenario2 = mustBeTransitioned(FS_CAPABILITY_3_C);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_3_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));
  }

  /*
   * FS to FS (System to Logical), simple-select, transition again, check that the references are not broken
   */
  private void step2() {
    performFStoFSTransition(Arrays.asList(getObject(FS_CAPABILITY_3_B)));

    Scenario scenario1 = mustBeTransitioned(FS_CAPABILITY_3_B);
    Scenario scenario2 = mustBeTransitioned(FS_CAPABILITY_3_C);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_3_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));
  }

  /*
   * FS to FS (System to Logical), multi-select
   */
  private void step3() {
    performFStoFSTransition(
        Arrays.asList(getObject(FS_CAPABILITY_3), getObject(FS_CAPABILITY_1_PKG), getObject(CAPABILITY_PKG3)));

    Scenario scenario1 = mustBeTransitioned(FS_CAPABILITY_3);
    Scenario scenario2 = mustBeTransitioned(FS_CAPABILITY_3_A);
    Scenario scenario3 = mustBeTransitioned(FS_CAPABILITY_1_PKG);
    Scenario scenario4 = mustBeTransitioned(FS_CAPABILITY_1_PKG2);
    Scenario scenario5 = mustBeTransitioned(FS_CAPABILITY_1_PKG3_1);
    Scenario scenario6 = mustBeTransitioned(FS_CAPABILITY_3_B);
    Scenario scenario7 = mustBeTransitioned(FS_CAPABILITY_3_C);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_3,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_3_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario3));

    InteractionUse reference3 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_1_PKG,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference3.getReferencedScenario().equals(scenario4));

    InteractionUse reference4 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_1_PKG3_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario4);
    assertTrue(reference4.getReferencedScenario().equals(scenario7));

    InteractionUse reference5 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_1_PKG3_1_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario4);
    assertTrue(reference5.getReferencedScenario().equals(scenario6));

    InteractionUse reference7 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_3_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario6);
    assertTrue(reference7.getReferencedScenario().equals(scenario7));
  }

  /*
   * FS to ES
   */
  private void step4() {
    performFStoESTransition(Arrays.asList(getObject(FS_CAPABILITY_4)));

    Scenario scenario1 = mustBeTransitioned(FS_CAPABILITY_4);
    Scenario scenario2 = mustBeTransitioned(FS_CAPABILITY_4_A);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_4,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_FS_CAPABILITY_4_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario1));
  }
}
