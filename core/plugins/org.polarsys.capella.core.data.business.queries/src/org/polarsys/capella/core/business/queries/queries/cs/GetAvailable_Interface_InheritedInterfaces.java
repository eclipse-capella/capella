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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveIndirectSuperTypesFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveSubTypesFilter;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * <p>
 * Gets all the Interfaces contained by the Interface Package (and all of its subPackages) of the current Element's parents hierarchy.
 * </p>
 * <p>
 * Gets all the Interfaces contained by the Interface Package (and all of its subPackages) of the current Element's parent (can be a Component, a Block
 * Architecture root package or the SystemEngineering package).
 * </p>
 * <p>
 * All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Element's parents hierarchy.
 * </p>
 * <p>
 * All the Interfaces contained by the Shared Package (and all of its sub-packages).
 * </p>
 * <p>
 * Except the current Interface itself,  Interfaces that specialize the current interface,
 * and Interfaces that are indirect generalizations of the current Interface.
 * </p>
 * <p>
 * Refer MQRY_Interface_Inherited_1
 * </p>
 * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
 */
public class GetAvailable_Interface_InheritedInterfaces extends AbstractQuery {

  @Override
  public List<Object> execute(Object inputElement, IQueryContext context) {
    List<EObject> availableElements = new ArrayList<EObject>();
    if (inputElement instanceof Interface) {
      Interface currentInterface = (Interface) inputElement;
      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentInterface);
      if (systemEngineering != null) {

        InterfacePkg interfacePkg = InterfaceExt.getRootOwnerInterfacePkg(currentInterface);
        if (interfacePkg != null) {

          // All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Element.
          availableElements.addAll(InterfacePkgExt.getAllInterfaces(interfacePkg));
          // All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Element's parent (can be a Component, a Block
          // Architecture Decomposition package, or a Block Architecture root package).
          BlockArchitecture parentBlockArchitecture = InterfacePkgExt.getRootComponentArchitecture(interfacePkg);
          if (parentBlockArchitecture != null) {
            for (BlockArchitecture block : BlockArchitectureExt.getRootAndPreviousBlockArchitectures(parentBlockArchitecture)) {
              availableElements.addAll(InterfacePkgExt.getAllInterfaces(block.getOwnedInterfacePkg()));
            }
          }
          Component parentComponent = InterfacePkgExt.getRootComponent(interfacePkg);
          if (parentComponent != null) {
            availableElements.addAll(InterfacePkgExt.getAllInterfaces((parentComponent).getOwnedInterfacePkg()));
          }
          // All the Interfaces contained by the Interface Package (and all of its sub-packages) of the current Element's parents hierarchy.
          availableElements.addAll(InterfacePkgExt.getOwnedInterfacesFromParentHierarchy(interfacePkg));
        }
      }
      availableElements = ListExt.removeDuplicates(availableElements);
      availableElements.remove(inputElement);
      MultiFilter filter = new MultiFilter(new IQueryFilter[] { new RemoveSubTypesFilter(currentInterface), new RemoveIndirectSuperTypesFilter(currentInterface) });
      availableElements = QueryInterpretor.executeFilter(availableElements, filter);
    }
    return (List) availableElements;
  }

}
