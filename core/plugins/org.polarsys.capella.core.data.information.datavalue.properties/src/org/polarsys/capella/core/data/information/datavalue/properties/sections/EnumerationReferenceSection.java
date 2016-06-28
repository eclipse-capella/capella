/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.datavalue.properties.sections;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;

/**
 * The EnumerationReference section.
 */
public class EnumerationReferenceSection extends DataValueReferenceSection {

  /**
   * {@inheritDoc}
   */
  @Override
  public void loadData(EObject capellaElement) {
    super.loadData(capellaElement);

    _referencedValueField.loadData(capellaElement, DatavaluePackage.eINSTANCE.getEnumerationReference_ReferencedValue());
    _referencedPropertyField.loadData(capellaElement, DatavaluePackage.eINSTANCE.getEnumerationReference_ReferencedProperty());
  }

  /**
   * @see org.eclipse.jface.viewers.IFilter#select(java.lang.Object)
   */
  @Override
  public boolean select(Object toTest) {
    EObject eObjectToTest = super.selection(toTest);
    return ((eObjectToTest != null) && (eObjectToTest.eClass() == DatavaluePackage.eINSTANCE.getEnumerationReference()));
  }
}
