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
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
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
public class Exception_FE01_01 extends TopDownTransitionTestCase {
  private String id_rootsf = "bc8f77e1-82d7-4202-933c-691b2728a240";
  private String id_sf1 = "098bab11-3a55-4eb6-9675-a5a8e8d6eed0";
  private String id_sf1op = "ec5ce57f-0fab-4538-880e-c466189c898e";
  private String id_ex_sf1sf2 = "2dfc0f40-0fad-43e8-a73e-0bba9a7948b3";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_rootsf)));
    FunctionalExchange ex_sf1sf2 = getObject(id_ex_sf1sf2);
    AbstractFunction sf1 = getObject(id_sf1);
    FunctionOutputPort sf1op = getObject(id_sf1op);

    assertNotNull(NLS.bind(Messages.NullElement, "Exchange 1"), ex_sf1sf2);
    assertNotNull(NLS.bind(Messages.NullElement, "SF1"), sf1);

    // Exchange1 shouldn't be (re)transitioned
    NamedElement ex_sf1sf2t = ProjectionTestUtils.getAllocatingFunctionalExchange(ex_sf1sf2);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, ex_sf1sf2.getName()), ex_sf1sf2t);

    // SF1 shouldn't be (re)transitioned
    NamedElement sf1t = ProjectionTestUtils.getAllocatingFunction(sf1);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, sf1.getName()), sf1t);

    // SF1op shouldn't be (re)transitioned
    NamedElement sf1opt = ProjectionTestUtils.getAllocatedPort(sf1op);
    assertNull(NLS.bind(Messages.ShouldNotBeTransitioned, sf1op.getName()), sf1opt);
  }

}
