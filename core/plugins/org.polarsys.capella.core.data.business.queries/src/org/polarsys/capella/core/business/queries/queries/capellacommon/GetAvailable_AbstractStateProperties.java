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
package org.polarsys.capella.core.business.queries.queries.capellacommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.data.behavior.AbstractEvent;
import org.polarsys.capella.common.data.modellingcore.IState;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.QueryConstants;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_AbstractStateProperties extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement inputElement = (CapellaElement) input;
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(inputElement);
    if (arch != null) {
      for (BlockArchitecture block : BlockArchitectureExt.getAllAllocatedArchitectures(arch)) {
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(block, false);
        while (allContents.hasNext()) {
          Object object = allContents.next();
          if ((object instanceof ExchangeItem) || (object instanceof Operation)) {
            availableElements.add((CapellaElement) object);
          }
        }
      }
          TreeIterator<Object> allContents = EcoreUtil.getAllContents(arch, false);
          while (allContents.hasNext()) {
            Object object = allContents.next();
            if ((object instanceof AbstractEvent) && !(object instanceof Event) && !(object instanceof AbstractFunction)) {
              availableElements.add((CapellaElement) object);
            }
          }
        }
    
    if (inputElement instanceof IState) {
      List<CapellaElement> availableFunctions = QueryInterpretor.executeQuery(QueryConstants.GET_CURRENT__MODE__AVAILABLE_IN_STATES, inputElement, context);//$NON-NLS-1$
      availableElements.addAll(availableFunctions);
      List<CapellaElement> currentElements = QueryInterpretor.executeQuery("GetCurrent_AbstractStateProperties", inputElement, context);//$NON-NLS-1$
      availableElements.removeAll(currentElements);
    }
    return (List) availableElements;
  }
    /**
     * @param state_p
     * @param component_p
     * @return
     */
    private List<CapellaElement> getElementsFromComponentAndAncestors(State state_p, Component component_p) {
      List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
      Collection<Component> componentAncestors = ComponentExt.getComponentAncestors(component_p);
      componentAncestors.add(component_p);

      for (Component component : componentAncestors) {
        availableElements.addAll(component.getAllocatedFunctions());
      }
      return availableElements;
    }
}
