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
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 *  * - Create OperationalActivity into Operational Activities named OARoot
 * - Create OperationalActivity into OARoot named OA1
 * - Create OperationalActivity into OARoot named OA2
 * - Create OperationalActivity into OARoot named OA3
 * - Rename EntityPkg from Operational Analysis to Operational Entities
 * - Create Entity into Operational Entities named E1
 * - Create StateMachine into E1 named SM11
 * - Create Region into SM11 named R111
 * - Create Part into E1 named E2
 * - Create Part into E1 named E3
 * - Create Part into E1 named E4
 * - Rename RolePkg from Operational Analysis to Roles
 * - Create Role into Roles named Role 1
 * - Create ActivityAllocation into Role 1 to OA2
 * - Create Role into Roles named Role 2
 * - Create ActivityAllocation into Role 2 to OA3
 * - Create Entity into Operational Entities named E2
 * - Create ComponentFunctionalAllocation into E2 to OA1
 * - Create Entity into Operational Entities named E3
 * - Create RoleAllocation into E3 to Role 1
 * - Create Entity into Operational Entities named E4
 * - Create RoleAllocation into E4 to Role 1
 * 
 * - Performing system transition on E3 should transition OA2
 * - Performing actor transition on E4 should transition OA2
 * 
 * - Add OA4 on Role 1
 * 
 * Expected Result:\
 * - Performing on E3 should transition OA4 and allocates the function to allocating component of role
 * 
 * </pre>
 */
public class CreateRule_RA01_02 extends TopDownTransitionTestCase {
  private String id_e3 = "8a12341a-e720-476e-8dc8-27b362e8598e";
  private String id_e4 = "482c4dc7-c55c-46a2-bc56-fb64c3482905";
  private String id_oa4 = "0a68bdd7-f3ad-47a6-979b-e681f08ffa4b";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, true);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(CreateRule_RA01_01.class.getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
  }

  private void step1() {
    performRealizedBySystemTransition(Arrays.asList(getObject(id_e3)));
    NamedElement e3t = mustBeTransitioned(id_e3);
    NamedElement e4t = mustBeTransitioned(id_e4);
    NamedElement oa4t = (NamedElement) mustBeTransitionedTo(id_oa4, FaPackage.Literals.ABSTRACT_FUNCTION);

    assertTrue(NLS.bind(Messages.ShouldRealize, e3t.getName(), oa4t.getName()),
        ProjectionTestUtils.isAllocatingFunction((Component) e3t, (AbstractFunction) oa4t));
    assertTrue(NLS.bind(Messages.ShouldRealize, e4t.getName(), oa4t.getName()),
        ProjectionTestUtils.isAllocatingFunction((Component) e3t, (AbstractFunction) oa4t));
  }

}
