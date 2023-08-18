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
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SF1, SF11, SF111, SF112, SF1121, SF1122, SF12, SF121, SF122, SF1221, SF1222
 * - Create E1 (SF1222, SF1122)
 * - Create E2 (SF112, SF122)
 * 
 * - Perform functional transition on SF11
 * - Move SF11 into LF1
 * - Move SF12 into LF2
 * 
 * 
 * Expected Result:\
 * - SF1 should not be (re)transitioned (childs are transitioned)
 * - E1 E2 should be in a transitionable parent of SF1
 * 
 * </pre>
 * 
 */
public class Exception_ME01G_01 extends TopDownTransitionTestCase {
  private String id_rootsf = "494e74c5-b337-4f71-83e9-a62b6f6fd43c";
  private String id_sf1 = "30d2150d-eb9f-439a-8233-0791f369ab5b";
  private String id_sf11 = "cf3f2977-1f8f-43db-9754-01bbb4f21a23";
  private String id_sf12 = "1a636ba3-0b28-49db-acaf-eb0ab4a1eee5";
  private String id_e1 = "ce22fba6-5d2b-478b-ab0e-59e90557fb2d";
  private String id_lf1 = "7df8ca2e-a4c9-470b-8f58-ce818b962929";
  private String id_lf2 = "81095517-0452-4df6-9d9b-e86e0e655af3";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_sf11)));
    AbstractFunction lf1 = getObject(id_lf1);
    assertNotNull(NLS.bind(Messages.NullElement, "LF1"), lf1);
    AbstractFunction lf2 = getObject(id_lf2);
    assertNotNull(NLS.bind(Messages.NullElement, "LF2"), lf2);

    // ROOT SYSTEM FUNCTION must be transitioned
    AbstractFunction rootsf = getObject(id_rootsf);
    assertNotNull(NLS.bind(Messages.NullElement, "ROOT SYSTEM FUNCTION"), rootsf);
    NamedElement rootsft = ProjectionTestUtils.getAllocatingFunction(rootsf);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, rootsf.getName()), rootsft);

    // SF1 must be transitioned
    AbstractFunction sf1 = getObject(id_sf1);
    assertNotNull(NLS.bind(Messages.NullElement, "SF1"), sf1);
    NamedElement sf1t = ProjectionTestUtils.getAllocatingFunction(sf1);
    assertNull(NLS.bind(Messages.ShouldBeTransitioned, sf1.getName()), sf1t);

    // SF11 must be transitioned
    AbstractFunction sf11 = getObject(id_sf11);
    assertNotNull(NLS.bind(Messages.NullElement, "SF11"), sf11);
    NamedElement sf11t = ProjectionTestUtils.getAllocatingFunction(sf11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf11.getName()), sf11t);

    // SF11 must be contained in LF1
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, sf11t.getName(), lf1.getName()), sf11t.eContainer() == lf1);

    // SF12 must be transitioned
    AbstractFunction sf12 = getObject(id_sf12);
    assertNotNull(NLS.bind(Messages.NullElement, "SF12"), sf12);
    NamedElement sf12t = ProjectionTestUtils.getAllocatingFunction(sf12);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf12.getName()), sf12t);

    // SF12 must be contained in LF2
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, sf12t.getName(), lf2.getName()), sf12t.eContainer() == lf2);

    // E1 must be transitioned
    FunctionalExchange e1 = getObject(id_e1);
    assertNotNull(NLS.bind(Messages.NullElement, "E1"), e1);
    NamedElement e1t = ProjectionTestUtils.getAllocatingFunctionalExchange(e1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, e1.getName()), e1t);

    // E1 must be contained in root function
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, e1t.getName(), "RootLF"), lf1.eContainer() == e1t.eContainer());
  }

}
