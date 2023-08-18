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
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create OperationalActivity into Operational Activities named OARoot
 * - Create OperationalActivity into OARoot named OA1
 * - Create OperationalActivity into OA1 named OA11
 * - Create OperationalActivity into OA1 named OA12
 * - Create OperationalActivity into OARoot named OA2
 * - Create OperationalActivity into OARoot named OA3
 * - Rename EntityPkg from Operational Analysis to Operational Entities
 * - Create Entity into Operational Entities named E1
 * - Create Part into E1 named E2
 * - Create Part into E1 named E3
 * - Create Entity into Operational Entities named E2
 * - Create ComponentFunctionalAllocation into E2 to OA1
 * - Create Entity into Operational Entities named E3
 * - Create ComponentFunctionalAllocation into E3 to OA2
 * - Create ComponentFunctionalAllocation into E3 to OA3
 * 
 * 
 * Expected Result:\
 * -  An entity transition on E2 should not perform allocation of owned CFE
 * -  An entity transition on E3 should perform allocation of owned CFEs
 * </pre>
 */
public class Exception_CFE01_01 extends TopDownTransitionTestCase {
  private String id_oa1 = "1f57eb45-59c2-4153-8401-d4e30c00434d";
  private String id_oa11 = "e77735fd-a5ea-40e8-92ea-998d2421090a";
  private String id_oa12 = "6003ee60-e53e-4a7e-98b5-8486363f0664";
  private String id_oa2 = "3ce1c7e2-cc5e-471f-96ae-19a5369bf16d";
  private String id_oa3 = "36a996aa-8efa-4f8c-8334-9eb4d4fd1318";

  private String id_e2 = "96076a48-03f5-4747-b36d-1445bc69e289";
  private String id_e3 = "0063d56d-4033-488c-af98-5d0bc7136d9a";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__FUNCTIONAL, true);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
    step2();
  }

  private void step1() {
    performRealizedBySystemTransition(Arrays.asList(getObject(id_e2)));
    NamedElement e2t = mustBeTransitioned(id_e2);

    shouldNotBeTransitioned(id_oa1); // as all leaf functions are already exist
    mustBeTransitioned(id_oa11);
    mustBeTransitioned(id_oa12);

    // but allocations shall not be added to System
    assertTrue(NLS.bind(Messages.ShouldNotAllocateFunctions, "E2"),
        ((Component) e2t).getOwnedFunctionalAllocation().size() == 0);
  }

  private void step2() {
    performOE2ActorTransition(Arrays.asList(getObject(id_e3)));
    NamedElement oa2t = mustBeTransitioned(id_oa2);
    NamedElement oa3t = mustBeTransitioned(id_oa3);
    NamedElement e3t = mustBeTransitioned(id_e3);

    // but allocations shall be added to Actor
    assertTrue(NLS.bind(Messages.ShouldRealize, e3t.getName(), "OA3"),
        ProjectionTestUtils.isAllocatingFunction((Component) e3t, (AbstractFunction) oa3t));
    assertTrue(NLS.bind(Messages.ShouldRealize, e3t.getName(), "OA2"),
        ProjectionTestUtils.isAllocatingFunction((Component) e3t, (AbstractFunction) oa2t));
  }

}
