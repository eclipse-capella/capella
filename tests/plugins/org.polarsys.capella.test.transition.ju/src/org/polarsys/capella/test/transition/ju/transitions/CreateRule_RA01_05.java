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

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 * - Create OperationalActivity into Operational Activities named OA1
 * - Create OperationalActivity into OA1 named OA11
 * - Create OperationalActivity into OA1 named OA12
 * - Create FunctionalExchange into OA1 named FE11 from  to 
 * - Rename EntityPkg from Operational Analysis to Operational Entities
 * - Create Entity into Operational Entities named E1
 * - Create StateMachine into E1 named SM11
 * - Create Region into SM11 named R111
 * - Create Part into E1 named part_P11 typed by E2
 * - Create Part into E1 named part_P12 typed by E3
 * - Create CommunicationMean into E1 named CM11 from  to 
 * - Create ConnectionFunctionalExchangeAllocation into CM11 to FE11
 * - Create Entity into Operational Entities named E2
 * - Create ComponentFunctionalAllocation into E2 to OA11
 * - Create Entity into Operational Entities named E3
 * - Create RoleAllocation into E3 to Role 1
 * 
 * Expected Result:\
 * - Performing system transition on E2 should not transition OA11
 * - Performing actor transition on E3 should not transition OA12
 * 
 * 
 * </pre>
 */
public class CreateRule_RA01_05 extends TopDownTransitionTestCase {
  private String id_oa11 = "8fbfe05e-f42f-462a-961c-c348f23c7ec8";
  private String id_oa12 = "91cd624f-3e17-44e9-a14f-41ee709768a9";
  private String id_e2 = "c7c474a5-65c3-45dc-bd45-7e6b1045ba6e";
  private String id_e3 = "68cff37b-a183-4a25-b184-6f3ac059df64";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, false);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(CreateRule_RA01_01.class.getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
    step2();
  }

  private void step1() {
    performRealizedBySystemTransition(Arrays.asList(getObject(id_e2)));
    mustBeTransitionedTo(id_e2, CtxPackage.Literals.SYSTEM_COMPONENT);
    shouldNotBeTransitioned(id_oa11, CtxPackage.Literals.SYSTEM_FUNCTION);
  }

  private void step2() {
    performRealizedBySystemTransition(Arrays.asList(getObject(id_e3)));
    mustBeTransitionedTo(id_e3, CtxPackage.Literals.SYSTEM_COMPONENT);
    shouldNotBeTransitioned(id_oa12, CtxPackage.Literals.SYSTEM_FUNCTION);
  }

}
