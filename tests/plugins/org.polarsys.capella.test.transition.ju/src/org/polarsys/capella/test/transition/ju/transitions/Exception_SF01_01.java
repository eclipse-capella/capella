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
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the functional transition - CreateRule-SF01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SF1, SF11, SF111, SF112
 * - Perform functional transition on root
 * - Create SF12
 * - Create LF1 and dragNDrop SF11t into LF1
 * - Delete SF1t
 * 
 * Expected Result:\
 * -  A functional transition on root should recreate SF1t and SF12t
 * </pre>
 * 
 */
public class Exception_SF01_01 extends TopDownTransitionTestCase {
  private String id_rootsf = "91ecd921-cab2-4473-b42b-3d92ae587b3a";
  private String id_sf1 = "2c17f343-398d-4684-8161-94dfc52ce889";
  private String id_sf12 = "111114d0-97a3-4e31-8c91-8389e26ff336";
  private String id_lf1 = "6b28e46f-f6d6-4b16-90e0-2564c9811cb0";

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
    assertNotNull(NLS.bind(Messages.NullElement, "LF1"), lf1);
    assertNotNull(NLS.bind(Messages.NullElement, "SF1"), sf1);

    NamedElement sf1t = ProjectionTestUtils.getAllocatingFunction(sf1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf1.getName()), sf1t);

    // SF1 must be (re)transitioned and should be different of lf1
    assertTrue(NLS.bind(Messages.ShouldBeRealizedBy, sf1.getName(), sf1t.getName()), sf1t != lf1);

    NamedElement sf12t = ProjectionTestUtils.getAllocatingFunction(sf12);
    // SF12 must be transitioned
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf12.getName()), sf12t);

    // SF12t.eContainer must be SF1t
    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, sf12t.getName(), sf1t.getName()), sf12t.eContainer() == sf1t);
  }

}
