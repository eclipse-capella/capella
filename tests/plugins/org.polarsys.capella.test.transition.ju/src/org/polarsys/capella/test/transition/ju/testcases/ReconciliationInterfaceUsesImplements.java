/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.testcases;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.transition.system.topdown.constants.ITopDownConstants;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.model.ModelReconciliationInterfacelinks;

/**
 */
public class ReconciliationInterfaceUsesImplements extends ModelReconciliationInterfacelinks {

  @Override
  public void performTest() throws Exception {
    LCPCTransition1();
    LCPCTransition2();
    LCPCTransition3();
  }

  /**
   * Perform trnasition to PC without interfaces should : - not recreate interface use/impl to PI1 PI2 - not recreate
   * interface use/impl to I3 I4 - create use/impl to I6 I4
   */
  protected void LCPCTransition1() {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, Boolean.FALSE);

    performLCtoPCTransition(getObjects(LA__LC1));

    Component lc1 = shouldExist(LA__LC1);
    Component pc1 = shouldExist(PA__PC1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.implementedInterfaces", "3"),
        pc1.getOwnedInterfaceImplementations().size() == 3);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.usedInterfaces", "3"), pc1.getOwnedInterfaceUses().size() == 3);

    Interface i11 = shouldExist(LA__IP1__I11);
    Interface i12 = shouldExist(LA__IP1__I12);
    Interface i13 = shouldExist(LA__IP1__I13);
    Interface i14 = shouldExist(LA__IP1__I14);
    Interface i15 = shouldExist(LA__IP1__I15);
    Interface i16 = shouldExist(LA__IP1__I16);
    Interface pi11 = shouldExist(PA__IP1__PI11);
    Interface pi12 = shouldExist(PA__IP1__PI12);

    EObject res = null;

    // A RealizationLink between InterfaceImplementation1
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PI1"),
        pi11.equals(((InterfaceImplementation) getObject(PA__PC1__INTERFACE_IMPLEMENTATION_TO_PI11)).getImplementedInterface()));

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PI2"), pi12.equals(((InterfaceUse) getObject(PA__PC1__INTERFACE_USE_TO_PI12)).getUsedInterface()));

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "I3"),
        i13.equals(((InterfaceImplementation) getObject(PA__PC1__INTERFACE_IMPLEMENTATION_TO_I13)).getImplementedInterface()));

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "I4"), i14.equals(((InterfaceUse) getObject(PA__PC1__INTERFACE_USE_TO_I14)).getUsedInterface()));

    res = mustBeTransitioned(LA__LC1__INTERFACE_IMPLEMENTATION_TO_I15);
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "I5"),
        i15.equals(((InterfaceImplementation) res).getImplementedInterface()));

    res = mustBeTransitioned(LA__LC1__INTERFACE_USE_TO_I16);
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "I6"), i16.equals(((InterfaceUse) res).getUsedInterface()));

  }

  protected void LCPCTransition2() {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, Boolean.TRUE);

    performLCtoPCTransition(getObjects(LA__LC1));

    Component lc1 = shouldExist(LA__LC1);
    Component pc1 = shouldExist(PA__PC1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.implementedInterfaces", "3"),
        pc1.getOwnedInterfaceImplementations().size() == 3);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.usedInterfaces", "3"), pc1.getOwnedInterfaceUses().size() == 3);

    Interface i11 = shouldExist(LA__IP1__I11);
    Interface i12 = shouldExist(LA__IP1__I12);
    Interface i13 = shouldExist(LA__IP1__I13);
    Interface i14 = shouldExist(LA__IP1__I14);
    Interface i15 = shouldExist(LA__IP1__I15);
    Interface i16 = shouldExist(LA__IP1__I16);

    Interface pi11 = shouldExist(PA__IP1__PI11);
    Interface pi12 = shouldExist(PA__IP1__PI12);

    Interface pi13 = mustBeTransitioned(LA__IP1__I13);
    Interface pi14 = mustBeTransitioned(LA__IP1__I14);
    Interface pi15 = mustBeTransitioned(LA__IP1__I15);
    Interface pi16 = mustBeTransitioned(LA__IP1__I16);

    EObject res = null;

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PI1"),
        pi11.equals(((InterfaceImplementation) getObject(PA__PC1__INTERFACE_IMPLEMENTATION_TO_PI11)).getImplementedInterface()));

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PI2"), pi12.equals(((InterfaceUse) getObject(PA__PC1__INTERFACE_USE_TO_PI12)).getUsedInterface()));

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PI3"),
        pi13.equals(((InterfaceImplementation) getObject(PA__PC1__INTERFACE_IMPLEMENTATION_TO_I13)).getImplementedInterface()));

    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PI4"), pi14.equals(((InterfaceUse) getObject(PA__PC1__INTERFACE_USE_TO_I14)).getUsedInterface()));

    res = mustBeTransitioned(LA__LC1__INTERFACE_IMPLEMENTATION_TO_I15);
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PI5"),
        pi15.equals(((InterfaceImplementation) res).getImplementedInterface()));

    res = mustBeTransitioned(LA__LC1__INTERFACE_USE_TO_I16);
    assertTrue(NLS.bind(Messages.ShouldBeLinkedTo, "PC1", "PI6"), pi16.equals(((InterfaceUse) res).getUsedInterface()));

  }

  protected void LCPCTransition3() {

    setPreferenceValue(ITopDownConstants.OPTIONS_TRANSITION__INTERFACE, Boolean.FALSE);

    performLCtoPCTransition(getObjects(LA__LC2));

    Component pc1 = mustBeTransitioned(LA__LC1);
    Component pc2 = mustBeTransitioned(LA__LC2);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.implementedInterfaces", "3"),
        pc1.getOwnedInterfaceImplementations().size() == 3);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc2.implementedInterfaces", "1"),
        pc2.getOwnedInterfaceImplementations().size() == 1);

    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc1.usedInterfaces", "3"), pc1.getOwnedInterfaceUses().size() == 3);
    assertTrue(NLS.bind(Messages.ShouldBeEqualsTo, "pc2.usedInterfaces", "1"), pc2.getOwnedInterfaceUses().size() == 1);

  }
}
