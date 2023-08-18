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
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * 
 * - Create Interface into Interfaces named I1S
 * - Create Actor into Actors named A1
 * - Create InterfaceImplementation into A1 linked to I1S
 * - Perform transition of A1, to LA1
 * - LA1 should be linked to I1S
 * - Perform transition of LA1 to PA1
 * - PA1 should be linked to I1P
 * 
 * 
 * Expected Result:\
 * Perform transition on LA1 to PA1
 * - PA1 should be linked to I1P, not I1S
 * 
 * Perform transition on A1 to LA1
 * - LA1 should be linked to I1S, not I1P
 * 
 * 
 * </pre>
 */
public class UpdateRule_MEG01I_01 extends TopDownTransitionTestCase {
  private String id_i1s = "0390ead1-291c-4c79-a621-b28746ee57b3";
  private String id_a1 = "8af84245-101a-4d19-bb80-12dd2ea4d38b";
  private String id_la1 = "a8726e76-3f92-49a6-82ba-63fba1d1e74d";
  private String id_i1p = "b7c920c0-a6e7-451f-849e-5dbc92819eb6";
  private String id_pa1 = "7b5634a1-de29-4512-b5c9-e485ab06c949";

  private void initSession() {
    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, false);
  }

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(UpdateRule_MEG01EI_01.class.getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    initSession();
    step1();
    step2();
  }

  private void step1() {
    performActorTransition(Arrays.asList(getObject(id_la1)));
    Interface i1s = (Interface) shouldExist(id_i1s);
    Interface i1p = (Interface) shouldExist(id_i1p);

    Component la1 = (Component) shouldExist(id_la1);
    Component pa1 = (Component) shouldExist(id_pa1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo,
        CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS.getName(), "1"),
        la1.getOwnedInterfaceImplementations().size() == 1);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo,
        CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS.getName(), "1"),
        pa1.getOwnedInterfaceImplementations().size() == 1);

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, la1, i1s),
        i1s.equals(la1.getOwnedInterfaceImplementations().get(0).getImplementedInterface()));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, pa1, i1p),
        i1p.equals(pa1.getOwnedInterfaceImplementations().get(0).getImplementedInterface()));

  }

  private void step2() {
    performActorTransition(Arrays.asList(getObject(id_a1)));
    Interface i1s = (Interface) shouldExist(id_i1s);
    Interface i1p = (Interface) shouldExist(id_i1p);

    Component la1 = (Component) shouldExist(id_la1);
    Component pa1 = (Component) shouldExist(id_pa1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo,
        CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS.getName(), "1"),
        la1.getOwnedInterfaceImplementations().size() == 1);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo,
        CsPackage.Literals.COMPONENT__OWNED_INTERFACE_IMPLEMENTATIONS.getName(), "1"),
        pa1.getOwnedInterfaceImplementations().size() == 1);

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, la1, i1s),
        i1s.equals(la1.getOwnedInterfaceImplementations().get(0).getImplementedInterface()));
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, pa1, i1p),
        i1p.equals(pa1.getOwnedInterfaceImplementations().get(0).getImplementedInterface()));

  }

}
