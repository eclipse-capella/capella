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
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create Mode11 Mode12 Mode13 Mode14 Mode15 Mode16
 * - Create T1 (Mode11, Mode12)
 * - Create T2 (Mode14, Mode15)
 * 
 * - Perform StateMachine transition
 * - Move T1 target to Mode13
 * - Move T2 target to Mode16
 * - Copy Mode16t
 * - Replace target of T2 to 'Copy of Mode16t'
 * 
 * - Move T1 target to Mode3
 * 
 * Expected Result:\
 * - T1t target should be moved from Mode2t to Mode3t
 * - T2t target should not be moved since 'Copy of Mode6t' realize Mode6
 * 
 * </pre>
 * 
 */
public class UpdateRule_ST01_01 extends TopDownTransitionTestCase {
  private String id_system_state_machine = "a278c867-f0ac-4b05-a294-180ab1058828";
  private String id_m13 = "9121605c-b127-4122-9d78-0dead670c069";
  private String id_m16 = "28b66c0d-c3b6-4115-b6d4-719bf9d2cd7d";
  private String id_T1 = "ac273996-f957-432f-b700-e7864ffe236b";
  private String id_T2 = "8ae610c6-6739-4e9d-9ef3-da35441ae513";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performStateMachineTransition(Arrays.asList(getObject(id_system_state_machine)));
    // T1 must be transitioned
    StateTransition t1 = getObject(id_T1);
    assertNotNull(NLS.bind(Messages.NullElement, "T1"), t1);
    StateTransition t1t = (StateTransition) ProjectionTestUtils.getAllocatingElement(t1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, "T1"), t1t);

    // m13 must be transitioned
    Mode m13 = getObject(id_m13);
    assertNotNull(NLS.bind(Messages.NullElement, "m13"), m13);
    Mode m13t = (Mode) ProjectionTestUtils.getAllocatingElement(m13);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, "m13"), m13t);

    // t1t.target should be updated to m13t
    assertTrue(NLS.bind(Messages.ShouldRealize, "t1t.target", m13.getName()), t1t.getTarget() == m13t);

    // T2 must be transitioned
    StateTransition t2 = getObject(id_T2);
    assertNotNull(NLS.bind(Messages.NullElement, "T2"), t2);
    StateTransition t2t = (StateTransition) ProjectionTestUtils.getAllocatingElement(t2);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, "T2"), t2t);

    // m16 must be transitioned
    Mode m16 = getObject(id_m16);
    assertNotNull(NLS.bind(Messages.NullElement, "m16"), m16);
    Mode m16t = (Mode) ProjectionTestUtils.getAllocatingElement(m16);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, "m16"), m16t);

    // t2t.target should be updated to m13t
    assertTrue(NLS.bind(Messages.ShouldRealize, "t2t.target", m16.getName()), !t2t.getTarget().getName().equals("M16")); //$NON-NLS-2$

  }

}
