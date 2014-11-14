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
package org.polarsys.capella.core.libraries.extendedqueries.information;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datatype.PhysicalQuantity;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetAvailable_PhysicalQuantity_Unit__Lib extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * <p>
   * Gets all the Units contained by the Value Package (and all of its sub-packages) of the current Element's parent (can be a Component, a Component
   * Architecture Decomposition package, or a Component Architecture root package).
   * </p>
   * <p>
   * All the Units contained by the Value Package (and all of its sub-packages) of the current Element's parents hierarchy according to layer visibility and
   * multiple decomposition rules.
   * </p>
   * <p>
   * All the Units contained by the Value Package (and all of its sub-packages) of the Shared Assets Package.
   * </p>
   * <p>
   * Except the current value itself
   * </p>
   * <p>
   * Refer MQRY_PhysicalDimension_DefaultUnit_1
   * </p>
   * @see org.polarsys.capella.core.business.queries.capellacore.common.ui.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    BlockArchitecture currentBlock = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    IAbstractModel currentProject = ILibraryManager.INSTANCE.getAbstractModel(element_p);
    if (element_p instanceof PhysicalQuantity) {
      PhysicalQuantity currentElement = (PhysicalQuantity) element_p;
      java.util.Collection<IAbstractLibrary> libraries = ILibraryManager.INSTANCE.getAllReferencedLibraries(currentProject, true);
      for (IAbstractLibrary library : libraries) {
        BlockArchitecture correspondingBlock = (BlockArchitecture) QueryExt.getCorrespondingElementInLibrary(currentBlock, (CapellaLibrary) library);
        for (BlockArchitecture blockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(correspondingBlock)) {
          availableElements.addAll(getElementsFromBlockArchitecture(blockArchitecture, currentElement.getUnit()));
        }
      }
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, Unit link) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (null != arch) {
      DataPkg dataPkg = DataPkgExt.getDataPkgOfBlockArchitecture(arch);
      if (null != dataPkg) {
        for (Unit unit : DataPkgExt.getAllUnits(dataPkg)) {
          if (unit.equals(link)) {
            continue;
          }
          availableElements.add(unit);
        }
      }
    }
    return availableElements;
  }

}
