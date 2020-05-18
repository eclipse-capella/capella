/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.cs.properties.sections;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.core.properties.sections.AllocationSection;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.la.LaPackage;

/**
 * The SystemRealization section.
 */
public class PhysicalLinkRealizationSection extends AllocationSection {
  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == CsPackage.eINSTANCE.getPhysicalLinkRealization()));
  }
}
