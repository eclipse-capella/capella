/*******************************************************************************
 * Copyright (c) 2006, 2019, 2020 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.filters.IQueryFilter;
import org.polarsys.capella.common.queries.filters.MultiFilter;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.QueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.queries.QueryIdentifierConstants;
import org.polarsys.capella.core.model.helpers.queries.filters.PreviousInterfacesForImplementationFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.PreviousInterfacesForUseFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveAllocatedInterfacesFilter;
import org.polarsys.capella.core.model.utils.ListExt;

public class AbstractComponentHelpers {

  /**
   * <p>
   * Gets All the Interfaces contained in the Interface Package (and all of its sub-packages) of the Physical
   * Architecture layer.
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
  public static List<EObject> getAvailableElements_Component_ImplementedInterface(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    EObject input = element;
    BlockArchitecture block = BlockArchitectureExt.getRootBlockArchitecture(element);
    IModel currentProject = ILibraryManager.INSTANCE.getModel(input);

    final Component component = (Component) input;
    MultiFilter filter = new MultiFilter(new IQueryFilter[] { new RemoveAllocatedInterfacesFilter(),
        new PreviousInterfacesForImplementationFilter(component) });

    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
    for (IModel library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(block, (CapellaModel) library);
      List<Interface> interfaces = QueryInterpretor.executeQuery(
          QueryIdentifierConstants.GET_ALL_INTERFACES_FROM_ARCHITECTURE, correspondingInput, new QueryContext());
      availableElements.addAll(interfaces);

      if (correspondingInput instanceof PhysicalArchitecture) {
        BlockArchitecture logicalBlock = BlockArchitectureExt
            .getPreviousBlockArchitecture((BlockArchitecture) correspondingInput);
        List<Interface> logicalInterfaces = QueryInterpretor.executeQuery(
            QueryIdentifierConstants.GET_ALL_INTERFACES_FROM_ARCHITECTURE, logicalBlock, new QueryContext(), filter);
        availableElements.addAll(logicalInterfaces);
      }
    }

    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  /**
   * <p>
   * Gets All the Interfaces contained in the Interface Package (and all of its sub-packages) of the Physical
   * Architecture layer.
   * </p>
   * <p>
   * Except The interfaces that are already used by the current Physical Component.
   * </p>
   * <p>
   * Refer MQRY_ PhysicalComponent_UsedInterfaces_1
   * </p>
   * 
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
   */
  public static List<EObject> getAvailableElements_Component_UsedInterface(CapellaElement element) {
    List<EObject> availableElements = new ArrayList<EObject>();
    EObject input = element;
    BlockArchitecture block = BlockArchitectureExt.getRootBlockArchitecture(element);
    IModel currentProject = ILibraryManager.INSTANCE.getModel(input);

    final Component component = (Component) input;
    MultiFilter filter = new MultiFilter(
        new IQueryFilter[] { new RemoveAllocatedInterfacesFilter(), new PreviousInterfacesForUseFilter(component) });

    Collection<IModel> libraries = LibraryManagerExt.getAllActivesReferences(currentProject);
    for (IModel library : libraries) {
      EObject correspondingInput = QueryExt.getCorrespondingElementInLibrary(block, (CapellaModel) library);
      List<Interface> interfaces = QueryInterpretor.executeQuery(
          QueryIdentifierConstants.GET_ALL_INTERFACES_FROM_ARCHITECTURE, correspondingInput, new QueryContext());
      availableElements.addAll(interfaces);

      if (correspondingInput instanceof PhysicalArchitecture) {
        BlockArchitecture logicalBlock = BlockArchitectureExt
            .getPreviousBlockArchitecture((BlockArchitecture) correspondingInput);
        List<Interface> logicalInterfaces = QueryInterpretor.executeQuery(
            QueryIdentifierConstants.GET_ALL_INTERFACES_FROM_ARCHITECTURE, logicalBlock, new QueryContext(), filter);
        availableElements.addAll(logicalInterfaces);
      }
    }

    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

}
