/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.business.abstractqueries.ExtendedBusinessQueryForLib;
import org.polarsys.capella.core.business.abstractqueries.RefactorDebugger;
import org.polarsys.capella.core.business.abstractqueries.RefactoredBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * This is the query for numeric types inherited types
 */
public class NumericType_InheritedNumericType extends GeneralizableElement_AbstractInheritedType implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery {
  /**
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,boolean)
	 */
	public List<CapellaElement> getOldCurrentElements(CapellaElement element_p,
			boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (!systemEngineeringExists(element_p)) {
			return currentElements;
		}
		if (element_p instanceof GeneralizableElement) {
			GeneralizableElement generalizableElement = (GeneralizableElement) element_p;
			currentElements.addAll(generalizableElement.getSuper());
			currentElements = ListExt.removeDuplicates(currentElements);
			currentElements.remove(generalizableElement);
		}
		return currentElements;
	}

/**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return DatatypePackage.Literals.NUMERIC_TYPE;
  }

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    // Gets all Numeric Types
    List<CapellaElement> availableElements = super.getAvailableElements(element_p);
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    // But removes all <code>PhysicalQuantity</> instances
    for (CapellaElement melElement : availableElements) {
      if (!(melElement instanceof PhysicalQuantity)) {

        returnValue.add(melElement);
      }
    }
    return returnValue;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.information.GeneralizableElement_AbstractInheritedType#getAvailableEclassForSuperType()
   */
  @Override
  protected EClass getAvailableEclassForSuperType() {
    return DatatypePackage.Literals.NUMERIC_TYPE;
  }

@Override
public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
  return RefactorDebugger.callAndTestQuery("GetAvailable_NumericType_InheritedNumericType__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
}

@Override
public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
  return RefactorDebugger.callAndTestQuery("GetCurrent_NumericType_InheritedNumericType", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
}
}
