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
 * Perform IS to IS transition, check that the referenced scenarios were also transitioned;
 * 
 * * Expected Result: The referenced scenarios are also transitioned and after transition, the transitioned scenario
 * reference a valid scenario (at same level)
 * 
 */
public class ReferencedScenarios_03 extends TopDownTransitionTestCase {
  public static final String IS_CAPABILITY_1 = "6a2a4934-fd23-4c1a-b9e7-ab02c74980e6"; //$NON-NLS-1$
  public static final String IS_CAPABILITY_1_A = "5f9c5e23-a6c3-431f-85d0-dd52fa0546b6"; //$NON-NLS-1$
  public static final String IS_CAPABILITY_1_B = "a562e74c-07be-4f73-8ac2-e27acdfdb983"; //$NON-NLS-1$
  public static final String IS_CAPABILITY_1_PKG = "5c72a711-14d3-4341-8b24-ca0c4d570e3a"; //$NON-NLS-1$
  public static final String IS_CAPABILITY_1_PKG2 = "a4b1ef34-30d2-4b54-8722-180273e1331b"; //$NON-NLS-1$
  public static final String IS_CAPABILITY_2 = "772f3d8b-69bb-4488-9e57-bfaa1a0f4aff"; //$NON-NLS-1$
  public static final String IS_CAPABILITY_2_A = "0d498dfc-7fe1-4baf-940d-fcb8a03acaa1"; //$NON-NLS-1$

  public static final String INTERACTIONUSE_IS_CAPABILITY_1 = "35ce749b-2fad-474a-861c-4b0a7b739ec8"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_IS_CAPABILITY_1_A = "0c851147-e217-4f98-896f-5a90446ae7a1"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_IS_CAPABILITY_1_A_1 = "e37be1c0-b6fe-4839-a942-5069d07f26cb"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_IS_CAPABILITY_1_B = "d117f556-d57f-4a44-9fdc-ce49c7710ac5"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_IS_CAPABILITY_1_B_1 = "dd2bb3d4-cff6-48b5-8e0c-5f5b02d4f7a0"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_IS_CAPABILITY_1_PKG = "d16b7405-89ef-4d8e-a198-087edad17ef9"; //$NON-NLS-1$
  public static final String INTERACTIONUSE_IS_CAPABILITY_2 = "e24d4b7a-58d2-4b54-b3e8-1477f7ea61a7"; //$NON-NLS-1$

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

  /*
   * IS to IS (System to Logical), simple-select
   */
  private void step1() {
    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITY_2)));

    Scenario scenario1 = mustBeTransitioned(IS_CAPABILITY_2);
    Scenario scenario2 = mustBeTransitioned(IS_CAPABILITY_2_A);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_IS_CAPABILITY_2,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));
  }

  /*
   * IS to IS (System to Logical), simple-select, transition again, check that the references are not broken
   */
  private void step2() {
    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITY_2)));

    Scenario scenario1 = mustBeTransitioned(IS_CAPABILITY_2);
    Scenario scenario2 = mustBeTransitioned(IS_CAPABILITY_2_A);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_IS_CAPABILITY_2,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));
  }

  /*
   * IS to IS (System to Logical), multi-select
   */
  private void step3() {
    performIStoISTransition(Arrays.asList(getObject(IS_CAPABILITY_1), getObject(IS_CAPABILITY_1_B)));

    Scenario scenario1 = mustBeTransitioned(IS_CAPABILITY_1);
    Scenario scenario2 = mustBeTransitioned(IS_CAPABILITY_1_A);
    Scenario scenario3 = mustBeTransitioned(IS_CAPABILITY_1_B);
    Scenario scenario4 = mustBeTransitioned(IS_CAPABILITY_1_PKG);
    Scenario scenario5 = mustBeTransitioned(IS_CAPABILITY_1_PKG2);

    InteractionUse reference1 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_IS_CAPABILITY_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario1);
    assertTrue(reference1.getReferencedScenario().equals(scenario2));

    InteractionUse reference2 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_IS_CAPABILITY_1_A,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference2.getReferencedScenario().equals(scenario3));

    InteractionUse reference3 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_IS_CAPABILITY_1_A_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario2);
    assertTrue(reference3.getReferencedScenario().equals(scenario4));

    InteractionUse reference4 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_IS_CAPABILITY_1_B,
        InteractionPackage.Literals.INTERACTION_USE, scenario3);
    assertTrue(reference4.getReferencedScenario().equals(scenario2));

    InteractionUse reference5 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_IS_CAPABILITY_1_B_1,
        InteractionPackage.Literals.INTERACTION_USE, scenario3);
    assertTrue(reference5.getReferencedScenario().equals(scenario1));

    InteractionUse reference6 = (InteractionUse) mustBeTransitionedTo(INTERACTIONUSE_IS_CAPABILITY_1_PKG,
        InteractionPackage.Literals.INTERACTION_USE, scenario4);
    assertTrue(reference6.getReferencedScenario().equals(scenario5));
  }
}
