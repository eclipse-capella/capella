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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * This is an abstract query for generalizable elements inherited type.
 */
public abstract class GeneralizableElement_AbstractInheritedType extends CapellaElement_CurrentAndHigherLevelsQuery {

  /**
   * @see org.polarsys.capella.core.business.abstractqueries.CapellaElement_CurrentAndHigherLevelsQuery#getDataFromLevel(org.polarsys.capella.core.data.cs.BlockArchitecture,
   *      org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  @Override
  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    // First gets the available elements regarding the types
    List<CapellaElement> availableElemsInTermOfTypes =
        CapellaElementsHelperForBusinessQueries.getCapellaElementsInstancesOf(dataPkg_p, getAvailableEclassForSuperType(), capellaElement_p);
    // Then verify that there is no generalization cycle
    for (CapellaElement elem : availableElemsInTermOfTypes) {
      if (elem instanceof GeneralizableElement && capellaElement_p instanceof GeneralizableElement
          && GeneralizableElementExt.isInheritancyCycleCompatible((GeneralizableElement) elem, (GeneralizableElement) capellaElement_p)) {
        returnValue.add(elem);
      }
    }
    return returnValue;
  }

  /**
   * returns the available <code>EClass</code> for the inherited types
   * @return an <code>EClass</code> instance
   */
  protected abstract EClass getAvailableEclassForSuperType();

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
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
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEStructuralFeatures()
   */
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER_GENERALIZATIONS);
  }
}
