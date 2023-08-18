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

import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 * - Create ExchangeItem into Interfaces named EI1
 * - Rename LogicalComponent from Logical Architecture to Logical System
 * - Create StateMachine into Logical System named Logical System State Machine
 * - Create Region into Logical System State Machine named Default Region
 * - Create LogicalActor into Actors named LA 1
 * - Rename LogicalContext from Logical Architecture to Logical Context
 * - Create Connection into Logical Context named C 1 from ComponentPort 1(Logical System) to ComponentPort 1(LA 1)
 *    -> Linked to ExchangeItem(EI1)
 * - Create Part into Logical Context named Logical System typed by Logical System
 * - Create Part into Logical Context named LA 1 typed by LA 1
 * 
 * Perform Actor transition on Actor
 * 
 * Expected Result:\
 * - Performing LCPC transition on LogicalSystem shoudl trnasitioned C1 and not ExchangeItem
 * 
 * </pre>
 */
public class Context_EI01_05 extends TopDownTransitionTestCase {

  private String id_c_1 = "e0f3c79c-e138-4764-ba1c-4a8e9de42eff";
  private String id_logical_system = "a28e2677-a95a-41f3-9698-b11b2e318d7d";
  private String id_ei1 = "69084230-5be4-4179-98f7-c1e957211513";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__EXCHANGE_ITEM, false);
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__LCPC,
        ITopDownConstants.OPTIONS_TRANSITION__LCPC_BREAKDOWN);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_I01");
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
  }

  private void step1() {
    performLCtoPCTransition(Arrays.asList(getObject(id_logical_system)));
    mustBeTransitioned(id_c_1);
    shouldNotBeTransitioned(id_ei1);
  }

}
