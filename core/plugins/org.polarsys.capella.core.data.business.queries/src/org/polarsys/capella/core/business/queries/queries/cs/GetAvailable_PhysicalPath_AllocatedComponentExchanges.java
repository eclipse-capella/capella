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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;

public class GetAvailable_PhysicalPath_AllocatedComponentExchanges extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<Object> availableElements = getAvailableElements(capellaElement);
    return availableElements;
  }

  private List<Object> getAvailableElements(CapellaElement element) {
    if (element instanceof PhysicalPath) {
      return List.copyOf(getPhysicalPathAllocatedComponentExchanges((PhysicalPath) element));
    }
    return Collections.emptyList();
  }

  /**
   * @param element
   * @return
   */
  private Set<ComponentExchange> getPhysicalPathAllocatedComponentExchanges(PhysicalPath element) {
    Set<ComponentExchange> availableElements = new HashSet<>();
    List<Component> terminalPCs = new ArrayList<>();

    retreiveTerminalPCs(PhysicalPathExt.getFlatFirstPhysicalPathInvolvments(element), terminalPCs);
    retreiveTerminalPCs(PhysicalPathExt.getFlatLastPhysicalPathInvolvments(element), terminalPCs);

    for (int i = 0; i < terminalPCs.size() - 1; i++) {
      Component sourceComponent = terminalPCs.get(i);
      for (int j = i + 1; j < terminalPCs.size(); j++) {
        Component targetComponent = terminalPCs.get(j);
        availableElements.addAll(getAvailableExchanges(sourceComponent, targetComponent));
      }
    }
    return availableElements;
  }

  private void retreiveTerminalPCs(Collection<PhysicalPathInvolvement> physicalPathInvolvments,
      List<Component> terminalPCs) {
    for (PhysicalPathInvolvement first : physicalPathInvolvments) {
      AbstractPathInvolvedElement involvedElt = first.getInvolvedElement();
      if (involvedElt instanceof Part) {
        AbstractType pc = ((Part) involvedElt).getAbstractType();
        if ((pc instanceof Component) && !terminalPCs.contains(pc)) {
          terminalPCs.add((Component) pc);
        }
      }
    }
  }

  private Set<ComponentExchange> getAvailableExchanges(Component sourceComponent, Component targetComponent) {

    // all the descendants of the @sourceComponent
    List<Component> sourceDeployedElements = PartExt
        .getComponentsOfParts(ComponentExt.getAllSubUsedParts(sourceComponent, true));
    sourceDeployedElements.add(sourceComponent);

    // all the descendants of the @targetComponent
    List<Component> targetDeployedElements = PartExt
        .getComponentsOfParts(ComponentExt.getAllSubUsedParts(targetComponent, true));
    targetDeployedElements.add(targetComponent);

    // find the connection between sourceList and targetList of component
    Set<ComponentExchange> availableElements = new HashSet<>();
    availableElements.addAll(
        PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(sourceDeployedElements, targetDeployedElements));
    availableElements.addAll(
        PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(targetDeployedElements, sourceDeployedElements));

    return availableElements;
  }

}