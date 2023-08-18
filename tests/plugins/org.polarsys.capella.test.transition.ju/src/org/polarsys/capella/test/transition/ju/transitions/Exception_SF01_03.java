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
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SF1, SF11, SF12, SF111
 * - Perform functional transition on root
 * - Create SF112
 * - Create LF1 and dragNDrop SF12t,SF111t into LF1
 * - Delete SF1t,SF11t
 * 
 * Expected Result:\
 * -  A functional transition on root should recreate SF1t and SF11t and SF112t but without replace containers of SF111,SF12
 * </pre>
 * 
 */
public class Exception_SF01_03 extends TopDownTransitionTestCase {
  private String id_rootsf = "dbf6b670-8c02-456a-8838-268bab17eac9";
  private String id_sf1 = "ed0697c5-9106-4452-bbd3-501200039ce5";
  private String id_sf11 = "9bba2afd-4049-4d9c-ba43-731415291854";
  private String id_sf111 = "44d574ae-872d-4ca4-9f5e-d1ddec4181d4";
  private String id_sf112 = "d2649d7d-a8d1-4f42-82b7-a9ccafef1f42";
  private String id_sf12 = "42d83933-377e-47c7-abb6-3361bfd19966";
  private String id_lf1 = "aeaf487f-a929-4070-b34f-55fe4fb78ff4";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("Context_SF01");
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_rootsf)));
    AbstractFunction lf1 = getObject(id_lf1);
    AbstractFunction sf1 = getObject(id_sf1);
    AbstractFunction sf12 = getObject(id_sf12);
    AbstractFunction sf11 = getObject(id_sf11);
    AbstractFunction sf112 = getObject(id_sf112);
    AbstractFunction sf111 = getObject(id_sf111);

    assertNotNull(NLS.bind(Messages.NullElement, "LF1"), lf1);
    assertNotNull(NLS.bind(Messages.NullElement, "SF1"), sf1);

    // SF1 should be (re)transitionned
    NamedElement sf1t = ProjectionTestUtils.getAllocatingFunction(sf1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf1.getName()), sf1t);

    // SF11 should be (re)transitionned
    NamedElement sf11t = ProjectionTestUtils.getAllocatingFunction(sf11);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf11.getName()), sf11t);

    // SF112 should be transitionned
    NamedElement sf112t = ProjectionTestUtils.getAllocatingFunction(sf112);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf12.getName()), sf112t);

    // SF112 should be attached to SF11
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, ((AbstractNamedElement)getObject(id_sf1)).getName(), sf1t.getName()),
        sf112t.eContainer() == sf11t);

    // SF11 should be attached to SF1
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, ((AbstractNamedElement)getObject(id_sf1)).getName(), sf1t.getName()),
        sf11t.eContainer() == sf1t);

    // SF111 should be attached to LF1
    NamedElement sf111t = ProjectionTestUtils.getAllocatingFunction(sf111);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf111.getName()), sf111t);

    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, ((AbstractNamedElement)getObject(id_sf1)).getName(), sf1t.getName()),
        sf111t.eContainer() == lf1);

    // SF12 should be attached to LF1
    NamedElement sf12t = ProjectionTestUtils.getAllocatingFunction(sf12);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf12.getName()), sf12t);

    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, ((AbstractNamedElement)getObject(id_sf1)).getName(), sf1t.getName()),
        sf12t.eContainer() == lf1);

    // SF1t must not be LF1
    assertTrue(NLS.bind(Messages.ShouldNotBeRealizedBy, ((AbstractNamedElement)getObject(id_sf1)).getName(), sf1t.getName()),
        sf1t != lf1);
  }

}
