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
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Collection;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * This is the query for the Collection type inherited type
 */
public class Collection_InheritedCollection extends GeneralizableElement_AbstractInheritedType implements ExtendedBusinessQueryForLib, RefactoredBusinessQuery {

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
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getOldAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = super.getAvailableElements(element_p);
    if (element_p instanceof Collection) {
      Type type = ((Collection) element_p).getType();
      if (type instanceof GeneralizableElement) {
        List<CapellaElement> filteredElements = new ArrayList<CapellaElement>();
        for (CapellaElement availableElement : availableElements) {
          if (availableElement instanceof Collection) {
            Type availableElementType = ((Collection) availableElement).getType();
            if (availableElementType != null) {
              if (!availableElementType.equals(type) && !GeneralizableElementExt.getAllSuperGeneralizableElements((GeneralizableElement) type).contains(availableElementType)) {
                filteredElements.add(availableElement);
              }
            }
          }
        }
        availableElements.removeAll(filteredElements);
      }
    }
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InformationPackage.Literals.COLLECTION;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.information.GeneralizableElement_AbstractInheritedType#getAvailableEclassForSuperType()
   */
  @Override
  protected EClass getAvailableEclassForSuperType() {
    return InformationPackage.Literals.COLLECTION;
  }

@Override
public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
  return RefactorDebugger.callAndTestQuery("GetAvailable_Collection_InheritedCollection__Lib", element_p, getOldAvailableElements(element_p), getEClass(), getClass());//$NON-NLS-1$
}

@Override
public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
  return RefactorDebugger.callAndTestQuery("GetCurrent_Collection_InheritedCollection", element_p, getOldCurrentElements(element_p, false), getEClass(), getClass());//$NON-NLS-1$
}
}
