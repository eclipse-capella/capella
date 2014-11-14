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
package org.polarsys.capella.core.business.queries.capellacore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class EnumerationPropertyValue_Type implements IBusinessQuery {

	/**
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element_p instanceof EnumerationPropertyValue) {
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
      if (null != systemEngineering) {
        for (EObject elt : EObjectExt.getAll(systemEngineering, CapellacorePackage.Literals.ENUMERATION_PROPERTY_TYPE)) {
          availableElements.add((CapellaElement) elt);
        }
      }

      availableElements = ListExt.removeDuplicates(availableElements);
      EnumerationPropertyType type = ((EnumerationPropertyValue) element_p).getType();
      if (type != null) {
        availableElements.remove(type);
      }
    }

		return availableElements;
	}

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>(1);
    if (element_p instanceof EnumerationPropertyValue) {
      currentElements.add(((EnumerationPropertyValue) element_p).getType());
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacorePackage.Literals.ENUMERATION_PROPERTY_VALUE__TYPE);
  }
}
