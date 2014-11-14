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
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;

/**
 */
public class CapellaElement_Status implements IBusinessQuery {

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (null == element_p) {
      return availableElements;
    }
    
    Project project = (Project) EcoreUtil2.getFirstContainer(element_p, CapellamodellerPackage.Literals.PROJECT);
    if (null != project) {
      for (EnumerationPropertyType enumeration : project.getOwnedEnumerationPropertyTypes()) {
        if (enumeration.getName().equals(CapellaProjectHelper.PROGRESS_STATUS_KEYWORD)) {
          for (EnumerationPropertyLiteral literal : enumeration.getOwnedLiterals()) {
            availableElements.add(literal);
          }
        }
      }
    }

    return availableElements;
  }

	/**
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement, boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

	  currentElements.add(element_p.getStatus());

		return currentElements;
	}

	public EClass getEClass() {
		return CapellacorePackage.Literals.CAPELLA_ELEMENT;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacorePackage.Literals.CAPELLA_ELEMENT__STATUS);
	}
}
