/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveRealizedInterfaces;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalComponent_UsedInterfaces extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement element = (CapellaElement) input;
    List<EObject> availableElements = new ArrayList<EObject>();
    SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering(element);
    if (systemEngineering != null) {
      if (element instanceof PhysicalComponent) {
        PhysicalComponent currentPC = (PhysicalComponent) element;
        for (Component cpnt : CapellaElementExt.getComponentHierarchy(currentPC)) {
          InterfacePkg interfacePkg = cpnt.getOwnedInterfacePkg();
          if (interfacePkg != null) {
            availableElements.addAll(InterfacePkgExt.getAllInterfaces(interfacePkg));
          }
        }
        BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(currentPC);
        List<CapellaElement> allInterfaces = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_INTERFACES,
            currentBlock, context);
        availableElements.addAll(allInterfaces);
        EList<Component> lcs = currentPC.getRealizedComponents();
        if (lcs.isEmpty()) {
          currentBlock = BlockArchitectureExt.getPreviousBlockArchitecture(currentBlock);
          allInterfaces = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_INTERFACES, currentBlock,
              context);
          availableElements.addAll(allInterfaces);
        } else {
          for (Component logicalComponent : lcs) {
            availableElements.addAll(logicalComponent.getUsedInterfaces());
            availableElements.addAll(logicalComponent.getRequiredInterfaces());
          }
        }
      }
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements = QueryInterpretor.executeFilter(availableElements, new RemoveRealizedInterfaces());
    return (List) availableElements;
  }

}
