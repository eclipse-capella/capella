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
package org.polarsys.capella.core.business.queries.queries.capellacommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.model.helpers.ComponentExt;

public class GetAvailable_Mode_AvailableInStates extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    if (element instanceof State) {
      EObject eContainer = element.eContainer();
      while (eContainer != null && !(eContainer instanceof Component || eContainer instanceof Class)) {
        eContainer = eContainer.eContainer();
      }
      if ((eContainer instanceof Component)) {
        availableElements.addAll(getElementsFromComponentAndSubComponents((Component) eContainer));
      }
    }
    return availableElements;
  }

  /**
   * same level Visibility Layer
   * 
   * @param component
   */
  protected List<CapellaElement> getElementsFromComponentAndSubComponents(Component component) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Collection<Component> subComponents = ComponentExt.getAllSubUsedAndDeployedComponents(component);
    subComponents.add(component);

    for (Component subComponent : subComponents) {
      availableElements.addAll(subComponent.getAllocatedFunctions());
    }
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    if (element instanceof State) {
      for (EObject referencer : EObjectExt.getReferencers(element,
          FaPackage.Literals.ABSTRACT_FUNCTION__AVAILABLE_IN_STATES)) {
        currentElements.add((CapellaElement) referencer);
      }
    }
    return currentElements;
  }
}