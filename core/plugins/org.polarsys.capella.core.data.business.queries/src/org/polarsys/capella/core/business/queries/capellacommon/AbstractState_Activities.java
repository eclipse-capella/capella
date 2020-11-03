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
package org.polarsys.capella.core.business.queries.capellacommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.helpers.ComponentExt;

/**
 * To delete: abstract query 
 * @deprecated
 */
public abstract class AbstractState_Activities implements IBusinessQuery {

  /**
   * @param state
   * @param component
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(State state, Component component) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Collection<Component> componentAncestors = ComponentExt.getComponentAncestors(component);
    componentAncestors.add(component);

    for (Component cpnt : componentAncestors) {
      availableElements.addAll(cpnt.getAllocatedFunctions());
    }

    return availableElements;
  }

  /**
   * same level Visibility Layer
   * @param state
   */
  private List<CapellaElement> getRule_MQRY_StateTransition_AvailableEvents_11(State state) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    EObject eContainer = state.eContainer();
    if (eContainer != null) {

      while (!(eContainer instanceof Component)) {
        eContainer = eContainer.eContainer();
      }
      availableElements.addAll(getElementsFromBlockArchitecture(state, (Component) eContainer));
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  @Override
  public List<EObject> getAvailableElements(EObject element) {
    List<EObject> availableElements = new ArrayList<EObject>();

    if (element instanceof State) {
      availableElements.addAll(getRule_MQRY_StateTransition_AvailableEvents_11((State) element));
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getCurrentElements(EObject, boolean)
   */
  @Override
  public List<EObject> getCurrentElements(EObject element, boolean onlyGenerated) {
    List<EObject> currentElements = new ArrayList<EObject>();
    if (element instanceof State) {
      List<AbstractEvent> evts = ((State) element).getDoActivity();
      for (AbstractEvent evt : evts) {
        currentElements.add((CapellaElement) evt);
      }
    }
    return currentElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEStructuralFeature()
   */
  @Override
  public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(CapellacommonPackage.Literals.STATE__DO_ACTIVITY);
  }
}
