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
 * - Rename LogicalComponent from Logical Architecture to Logical System
 * - Create StateMachine into Logical System named Logical System State Machine
 * - Create Region into Logical System State Machine named Default Region
 * - Create SystemRealization into Logical System to System
 * 
 * 
 * Expected Result:\
 * - Performing LCPC transition on LogicalSystem shoudl trnasitioned SM
 * 
 * </pre>
 */
public class Context_SM01_02 extends TopDownTransitionTestCase {

  private String id_logical_system = "46553151-d5bf-4364-af91-7fccd83c025b";
  private String id_logical_system_state_machine = "16bcb268-cf8a-4cc2-aebf-baf2dd28cc9b";
  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__STATE_MACHINE, false);
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
    shouldNotBeTransitioned(id_logical_system_state_machine);
  }
}