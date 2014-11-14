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
package org.polarsys.capella.core.business.queries.capellacommon;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.behavior.AbstractEvent;

/**
 * This query is for StateTransition -> Effect and Trigger
 */
public abstract class AbstractStateTransitionTriggerAndEffect implements IBusinessQuery {

  /**
   * @param arch_p
   * @param state_p
   * @return
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, StateTransition transition_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    if (arch_p != null) {
      TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch_p, false);
      while (allContents.hasNext()) {
        Object object = allContents.next();
        if ((object instanceof AbstractEvent) && !(object instanceof Event)) {
          availableElements.add((CapellaElement) object);
        }
      }
    }

    // remove existing from the availableElements
    for (CapellaElement elt : getCurrentElements(transition_p, false)) {
      availableElements.remove(elt);
    }

    return availableElements;
  }

  /**
   * same level Visibility Layer
   * @param transition_p
   */
  protected List<CapellaElement> getRule_MQRY_StateTransition_AvailableEvents_11(StateTransition transition_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(transition_p);
    if (null != arch) {
      availableElements.addAll(getElementsFromBlockArchitecture(arch, transition_p));
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (element_p instanceof StateTransition) {
      availableElements.addAll(getRule_MQRY_StateTransition_AvailableEvents_11((StateTransition) element_p));
    }

    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return CapellacommonPackage.Literals.STATE_TRANSITION;
  }

}
