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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.libraries.IAbstractLibrary;
import org.polarsys.capella.common.libraries.IAbstractModel;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datatype.Enumeration;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.libraries.capellaModel.CapellaLibrary;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveUnnamedElementFilter;
import org.polarsys.capella.core.queries.helpers.QueryExt;

public class GetAvailable_EnumerationLiteralDomainValue__Lib extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement element_p = (CapellaElement) input;
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    // get all data in the root data package of each allocated architectures in libraries
    BlockArchitecture blockArchitectureInProject = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    IAbstractModel currentProject = ILibraryManager.INSTANCE.getAbstractModel(element_p);
    for (IAbstractLibrary library : ILibraryManager.INSTANCE.getAllReferencedLibraries(currentProject, true)) {
      BlockArchitecture blockArchitecture = QueryExt.getCorrespondingBlockArchitectureFromLibrary(blockArchitectureInProject, (CapellaLibrary) library);
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
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>(1);
    if (null != dataPkg_p) {
      DataType domainType = null;
      EObject owner = capellaElement_p.eContainer();
      if (owner instanceof Enumeration) {
        domainType = ((Enumeration) owner).getDomainType();
      }

      // The values typed by one of the current domain type ancestors or the domain type itself
      returnValue
          .addAll(CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, domainType, true, true, DatavaluePackage.Literals.DATA_VALUE, null));

      // The properties typed by by one of the current domain type ancestors or the domain type itself
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg_p, domainType, true));
      // In case the domain type is undefined all numeric values are supported
      if (null == domainType) {
        returnValue.addAll(CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg_p, DatavaluePackage.Literals.NUMERIC_VALUE, true, true));
      }
    }

    return returnValue;
  }

}