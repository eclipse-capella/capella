/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.model.helpers.StateExt;
import org.polarsys.capella.core.model.helpers.StateMachineExt;

public class GetAvailable_State_ReferencedStates extends AbstractQuery {

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
    if (element instanceof IState) {
      availableElements.addAll(getRule_MQRY_State_AvailableStates_11((IState) element));
    }
    return availableElements;
  }

  /**
   * same level Visibility Layer
   * 
   * @param state
   */
  protected List<CapellaElement> getRule_MQRY_State_AvailableStates_11(IState state) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Component ownerCpnt = (Component) EcoreUtil2.getFirstContainer(state, CsPackage.Literals.COMPONENT);
    if (null != ownerCpnt) {
      availableElements.addAll(getElementsFromComponent(ownerCpnt, state));
    } else {
      ComponentPkg ownerPkg = (ComponentPkg) EcoreUtil2.getFirstContainer(state, CsPackage.Literals.COMPONENT_PKG);
      if (null != ownerPkg) {
        availableElements.addAll(getElementsFromComponent(ownerPkg, state));
      }
    }
    return availableElements;
  }

  /**
   * @param arch
   * @param state
   * @return
   */
  protected List<CapellaElement> getElementsFromComponent(EObject arch, IState state) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (arch != null) {
      TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch, false);
      while (allContents.hasNext()) {
        Object object = allContents.next();
        if (object instanceof IState && object != state) {
          availableElements.add((CapellaElement) object);
        }
      }
    }
    return availableElements;
  }
}