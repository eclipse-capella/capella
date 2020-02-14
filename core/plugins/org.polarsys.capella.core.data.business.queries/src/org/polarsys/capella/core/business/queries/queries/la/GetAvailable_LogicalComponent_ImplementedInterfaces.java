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

package org.polarsys.capella.core.business.queries.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveRealizedInterfaces;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * <p>
 * Gets All the Interfaces contained in the Interface Package (and all of its sub-packages) of the Physical Architecture
 * layer.
 * </p>
 * <p>
 * Except The interfaces that are already implemented by the current Physical Component.
 * </p>
 * <p>
 * Refer MQRY_ PhysicalComponent_ImplInterfaces_1
 * </p>
 * 
 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
 */
public class GetAvailable_LogicalComponent_ImplementedInterfaces extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement element = (CapellaElement) input;
    List<EObject> availableElements = new ArrayList<EObject>();
    SystemEngineering systemEngineering = SystemEngineeringExt.getSystemEngineering(element);
    if (systemEngineering != null) {
      if (element instanceof LogicalComponent) {
        // Gets All the Interfaces contained in the Interface Package (and all of its sub-packages) of the Physical
        // Architecture layer.
        // Except The interfaces that are already implemented by the current Physical Component.
        LogicalComponent currentPC = (LogicalComponent) element;
        for (Component cpnt : CapellaElementExt.getComponentHierarchy(currentPC)) {
          InterfacePkg interfacePkg = cpnt.getOwnedInterfacePkg();
          if (interfacePkg != null) {
            availableElements.addAll(InterfacePkgExt.getAllInterfaces(interfacePkg));
          }
        }
        BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(currentPC);
        if (currentBlock.getOwnedInterfacePkg() != null) {
          availableElements.addAll(InterfacePkgExt.getAllInterfaces(currentBlock.getOwnedInterfacePkg()));
        }
        currentBlock = BlockArchitectureExt.getPreviousBlockArchitecture(currentBlock);
        List<CapellaElement> allInterfaces = QueryInterpretor.executeQuery(QueryIdentifierConstants.GET_ALL_INTERFACES,
            currentBlock, context);
        availableElements.addAll(allInterfaces);
      }
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    availableElements = QueryInterpretor.executeFilter(availableElements, new RemoveRealizedInterfaces());
    return (List) availableElements;
  }

}
