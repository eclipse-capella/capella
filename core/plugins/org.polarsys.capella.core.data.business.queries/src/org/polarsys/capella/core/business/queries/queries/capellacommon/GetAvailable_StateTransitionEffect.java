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
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.Operation;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.ComponentPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_StateTransitionEffect extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement inputElement = (CapellaElement) input;
    List<Object> availableElements = new ArrayList<>();
    BlockArchitecture arch = SystemEngineeringExt.getRootBlockArchitecture(inputElement);
    if (arch != null) {
      for (BlockArchitecture block : BlockArchitectureExt.getAllAllocatedArchitectures(arch)) {
        TreeIterator<Object> allContents = EcoreUtil.getAllContents(block, false);
        while (allContents.hasNext()) {
          Object object = allContents.next();
          if ((object instanceof ExchangeItem) || (object instanceof Operation)) {
            availableElements.add(object);
          }
        }
      }
      EObject eContainer = inputElement.eContainer();
      while (eContainer != null && !(eContainer instanceof Component) && !(eContainer instanceof ComponentPkg) && !(eContainer instanceof Class)) {
        eContainer = eContainer.eContainer();
      }
      if ((eContainer instanceof Component) && (inputElement instanceof StateTransition)) {
        availableElements.addAll(getElementsFromComponentAndSubComponents((Component) eContainer));
      }
      if ((eContainer instanceof ComponentPkg) && (inputElement instanceof StateTransition)) {
        for (Component component: ComponentPkgExt.getSubDefinedComponents((ComponentPkg)eContainer)) {
          availableElements.addAll(getElementsFromComponentAndSubComponents((Component) component));
        }
      }
    }
    return availableElements;
  }

  /**
   * @param state
   * @param component
   * @return
   */
  private List<CapellaElement> getElementsFromComponentAndSubComponents(Component component) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    Collection<Component> subComponents = ComponentExt.getAllSubUsedAndDeployedComponents(component);
    subComponents.add(component);

    for (Component cpnt : subComponents) {
      availableElements.addAll(cpnt.getAllocatedFunctions());
    }
    return availableElements;
  }

}
