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
 * - Create SF1, SF11, SF12, SF111
 * - Perform functional transition on root
 * - Create LF1 and dragNDrop SF12t,SF111t into LF1
 * - Delete SF1t,SF11t
 * 
 * Expected Result:\
 * -  A functional transition on root shouldn't recreate SF1t and SF11t and not replace containers of SF111,SF12
 * </pre>
 * 
 */
public class Exception_SF01_04 extends TopDownTransitionTestCase {
  private String id_rootsf = "adc7d5b0-e170-4761-bbda-cd6843447cac";
  private String id_sf1 = "8a552246-eb3c-4ced-93d7-95f084719392";
  private String id_sf11 = "9fae4123-a55d-4b5b-8007-e15bb939cc62";
  private String id_sf111 = "cb5d2e73-b016-46b2-8800-964b941c2b7f";
  private String id_sf12 = "c634ac8f-c981-4aa8-b50a-b8e427fade07";
  private String id_lf1 = "a0e7f469-6c52-4f33-9af8-366cc750a171";

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
    AbstractFunction sf111 = getObject(id_sf111);

    assertNotNull(NLS.bind(Messages.NullElement, "LF1"), lf1);
    assertNotNull(NLS.bind(Messages.NullElement, "SF1"), sf1);

    // SF1 shouldn't be (re)transitionned
    NamedElement sf1t = ProjectionTestUtils.getAllocatingFunction(sf1);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, sf1.getName()), sf1t);

    // SF11 shouldn't be (re)transitionned
    NamedElement sf11t = ProjectionTestUtils.getAllocatingFunction(sf11);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, sf11.getName()), sf11t);

    // SF111 should be attached to LF1
    NamedElement sf111t = ProjectionTestUtils.getAllocatingFunction(sf111);
    assertNotNull(Messages.ShouldBeTransitioned, sf111t);

    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, sf111.getName(), sf111t.getName()), sf111t.eContainer() == lf1);

    // SF12 should be attached to LF1
    NamedElement sf12t = ProjectionTestUtils.getAllocatingFunction(sf12);
    assertNotNull(Messages.ShouldBeTransitioned, sf12t);

    assertTrue(NLS.bind(Messages.ShouldBeContainedBy, sf12.getName(), sf12t.getName()), sf12t.eContainer() == lf1);
  }

}
