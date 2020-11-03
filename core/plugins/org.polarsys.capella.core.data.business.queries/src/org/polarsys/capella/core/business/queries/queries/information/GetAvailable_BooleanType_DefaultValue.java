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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.interpretor.QueryInterpretor;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.business.abstractqueries.helpers.CapellaElementsHelperForBusinessQueries;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.queries.filters.KeepPrimitiveClassInstanceOfSpecificEClassFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveUnnamedElementFilter;

public class GetAvailable_BooleanType_DefaultValue extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    // GET THE PARAMETERS AND INITIALIZE RESULTING LIST
    CapellaElement element = (CapellaElement) input;
    List<EObject> result = new ArrayList<EObject>();
    // GET AVAILABLE ELEMENTS
    // get all data in the root data package of each allocated architectures
    BlockArchitecture currentBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(element);
    for (BlockArchitecture blockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(currentBlockArchitecture)) {
      DataPkg dataPkg = blockArchitecture.getOwnedDataPkg();
      if (dataPkg != null) {
        result.addAll(getDataFromLevel(dataPkg, element));
      }
    }
    // get all data and primitive types in the data packages of each parents components
    for (Component cpnt : CapellaElementExt.getComponentHierarchy(element)) {
      DataPkg dataPkg = cpnt.getOwnedDataPkg();
      if (dataPkg != null) {
        result.addAll(getDataFromLevel(dataPkg, element));
        List<EObject> allTypes = new ArrayList<EObject>();
        allTypes.addAll(DataPkgExt.getAllTypesFromDataPkg(dataPkg));
        allTypes = QueryInterpretor.executeFilter(allTypes, new KeepPrimitiveClassInstanceOfSpecificEClassFilter(InformationPackage.Literals.CLASS));
        allTypes = QueryInterpretor.executeFilter(allTypes, new KeepPrimitiveClassInstanceOfSpecificEClassFilter(InformationPackage.Literals.COLLECTION));
        result.addAll(allTypes);
      }
    }
    // get all values in the data packages of each parent components of realized components
    Component currentCpnt =
        (element instanceof Component) ? (Component) element : (Component) EcoreUtil2.getFirstContainer(element, CsPackage.Literals.COMPONENT);
    if (currentCpnt != null) {
      for (Component allocatedCpnt : currentCpnt.getRealizedComponents()) {
        List<Component> componentHierarchy = CapellaElementExt.getComponentHierarchy(allocatedCpnt);
        componentHierarchy.add(allocatedCpnt);
        for (Component cpnt : componentHierarchy) {
          DataPkg dataPkg = cpnt.getOwnedDataPkg();
          if (dataPkg != null) {
            for (CapellaElement data : getDataFromLevel(dataPkg, element)) {
              if (!result.contains(data)) {
                result.add(data);
              }
            }
          }
        }
      }
    }
    // get unlevelized data
    if (element instanceof BooleanType) {
      List<GeneralizableElement> rootSupertypes = GeneralizableElementExt.getRootSupertypes((BooleanType) element);
      rootSupertypes.add((GeneralizableElement) element);
      for (CapellaElement melElem : rootSupertypes) {
        if (melElem instanceof BooleanType) {
          BooleanType rootBooleanType = (BooleanType) melElem;
          result.addAll(rootBooleanType.getOwnedLiterals());
        }
      }
    }

    result = QueryInterpretor.executeFilter(result, new RemoveUnnamedElementFilter());
    return (List) result;
  }

  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg, CapellaElement dataType) {
    if (dataType instanceof BooleanType) {
      List<EClass> searchedUntypedEclasses = new ArrayList<EClass>();
      BooleanType booleanType = (BooleanType) dataType;
      searchedUntypedEclasses.add(DatavaluePackage.Literals.BOOLEAN_REFERENCE);
      searchedUntypedEclasses.add(DatavaluePackage.Literals.ABSTRACT_EXPRESSION_VALUE);
      List<CapellaElement> returnValue = CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg, searchedUntypedEclasses, true, false);
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg, booleanType, false, true,
          DatavaluePackage.Literals.BOOLEAN_REFERENCE, null));
      returnValue.addAll(CapellaElementsHelperForBusinessQueries.getPropertiesTypedBy(dataPkg, booleanType, false));
      return returnValue;
    }
    return Collections.emptyList();
  }

}