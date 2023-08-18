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

import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test contextuality of Actor transition
 * 
 * <pre>
 * Model is created with the following elements�
 * - Rename SystemContext from System Analysis to System Context
 * - Create Connection into System Context named C 1 from CP11(A1) to CP21(A2)
 * - Create Connection into System Context named C 2 from CP22(A2) to CP31(A3)
 * - Create Connection into System Context named C 3 from CP32(A3) to CP1(System)
 * - Create Connection into System Context named C 4 from CP51(A5) to CP41(A4)
 * - Create Part into System Context named System typed by System
 * - Create Part into System Context named A1 typed by A1
 * - Create Part into System Context named A2 typed by A2
 * - Create Part into System Context named A3 typed by A3
 * - Create Part into System Context named A4 typed by A4
 * - Create Part into System Context named A5 typed by A5
 * - Rename System from System Analysis to System
 * - Create StateMachine into System named SM1
 * - Create Region into SM1 named R11
 * - Create ComponentPort into System named CP1
 * - Rename ActorPkg from System Analysis to Actors
 * - Create Actor into Actors named A1
 * - Create ComponentPort into A1 named CP11
 * - Create Actor into Actors named A2
 * - Create ComponentPort into A2 named CP21
 * - Create ComponentPort into A2 named CP22
 * - Create Actor into Actors named A3
 * - Create ComponentPort into A3 named CP31
 * - Create ComponentPort into A3 named CP32
 * - Create Actor into Actors named A4
 * - Create ComponentPort into A4 named CP41
 * - Create Actor into Actors named A5
 * - Create ComponentPort into A5 named CP51

 * 
 * Expected Result:\
 * - Performing actor transition on A4 should transition A4 and ports only
 * - Performing actor transition on A5 should transition A5 and ports and C4
 * 
 * </pre>
 * 
 */
public class ActorTransitionWithGeneralizationTest extends TopDownTransitionTestCase {

  private String id_A1 = "aa66bb91-eaf3-4ba3-b039-70a5118328e5"; //$NON-NLS-1$
  private String id_GENERALIZATION_TO_A3 = "4ef5d5bd-79f2-4685-8b1c-d55ea1352239"; //$NON-NLS-1$ 


  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("ActorTransition_01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performActorTransition(Arrays.asList(getObject(id_A1)));
    mustBeTransitioned(id_GENERALIZATION_TO_A3); 
  }

}
