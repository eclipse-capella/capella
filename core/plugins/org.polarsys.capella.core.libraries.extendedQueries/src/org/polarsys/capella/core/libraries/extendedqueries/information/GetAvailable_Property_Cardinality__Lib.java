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

import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.libraries.manager.LibraryManagerExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveUnnamedElementFilter;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetAvailable_Property_Cardinality__Lib extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement element_p = (CapellaElement) input;
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    // get all data in the root data package of each allocated architectures in libraries
    BlockArchitecture blockArchitectureInProject = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    IModel currentProject =  ILibraryManager.INSTANCE.getModel(element_p);
    for (IModel library : LibraryManagerExt.getAllActivesReferences(currentProject)) {
      BlockArchitecture blockArchitecture = QueryExt.getCorrespondingBlockArchitectureFromLibrary(blockArchitectureInProject, (CapellaModel) library);
      for (BlockArchitecture currentBlockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(blockArchitecture)) {
        DataPkg dataPkg = currentBlockArchitecture.getOwnedDataPkg();
        if (dataPkg != null) {
          result.addAll(getDataFromLevel(dataPkg, element_p));
        }
      }
    }
    result = QueryInterpretor.executeFilter(result, new RemoveUnnamedElementFilter());
    return (List) result;
  }

  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p) {
    List<CapellaElement> returnValue = CapellaElementsHelperForBusinessQueries.getApplicableValuesForCardinalitiesInLevel(dataPkg_p);
    returnValue.addAll(CapellaElementsHelperForBusinessQueries.getApplicablePropertiesForCardinalitiesInLevel(dataPkg_p));
    return returnValue;
  }
}