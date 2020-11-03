/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * The StringReference section.
 */
public class StringReferenceSection extends DataValueReferenceSection {

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _referencedValueField.loadData(capellaElement, DatavaluePackage.eINSTANCE.getStringReference_ReferencedValue());
    _referencedPropertyField.loadData(capellaElement, DatavaluePackage.eINSTANCE.getStringReference_ReferencedProperty());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatavaluePackage.eINSTANCE.getStringReference()));
  }
}
