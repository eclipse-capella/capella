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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.ExtendingQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.queries.queries.information.GetAvailable_NumericValue_Unit;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.data.information.datavalue.NumericValue;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.queries.QueryExt;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveUnnamedElementFilter;

@ExtendingQuery (extendingQuery = GetAvailable_NumericValue_Unit.class)
public class GetAvailable_NumericValue_Unit__Lib extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    NumericValue numericValue = (NumericValue) input;
    Unit unit = numericValue.getUnit();
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    // get all data in the root data package of each allocated architectures in libraries
    BlockArchitecture blockArchitectureInProject = BlockArchitectureExt.getRootBlockArchitecture(numericValue);
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(numericValue);
    for (IModel library : LibraryManagerExt.getAllActivesReferences(currentProject)) {
      BlockArchitecture blockArchitecture = QueryExt.getCorrespondingBlockArchitectureFromLibrary(blockArchitectureInProject, (CapellaModel) library);
      for (BlockArchitecture currentBlockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(blockArchitecture)) {
        DataPkg dataPkg = currentBlockArchitecture.getOwnedDataPkg();
        if (dataPkg != null) {
          result.addAll(getElementsFromBlockArchitecture(currentBlockArchitecture, unit));
        }
      }
    }
    result = QueryInterpretor.executeFilter(result, new RemoveUnnamedElementFilter());
    return (List) result;
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