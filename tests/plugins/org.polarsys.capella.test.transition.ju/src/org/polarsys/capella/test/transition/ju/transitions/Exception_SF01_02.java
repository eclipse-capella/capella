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
 * - Create LF1 and dragNDrop SF11t into LF1
 * - Delete SF1t
 * 
 * Expected Result:\
 * -  A functional transition on root shouldn't recreate SF1t
 * </pre>
 * 
 */
public class Exception_SF01_02 extends TopDownTransitionTestCase {
  private String id_rootsf = "2e2e8ff9-f168-402f-ad81-5ed0e96e8287";
  private String id_sf1 = "bb200569-294d-48c4-bb46-ff14e0e06c5a";
  private String id_lf1 = "33bb9e63-21a6-40f2-a54b-2096608b9a43";

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
    assertNotNull(NLS.bind(Messages.NullElement, "LF1"), lf1);
    assertNotNull(NLS.bind(Messages.NullElement, "SF1"), sf1);

    // SF1 shouldn't be (re)transitioned
    NamedElement sf1t = ProjectionTestUtils.getAllocatingFunction(sf1);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, sf1.getName()), sf1t);
  }

}
