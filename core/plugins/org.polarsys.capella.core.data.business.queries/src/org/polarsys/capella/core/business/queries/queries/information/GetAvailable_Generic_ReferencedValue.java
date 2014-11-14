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
package org.polarsys.capella.core.business.queries.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
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
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.AbstractExpressionValue;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.queries.filters.KeepPrimitiveClassInstanceOfSpecificEClassFilter;
import org.polarsys.capella.core.model.helpers.queries.filters.RemoveUnnamedElementFilter;

public class GetAvailable_Generic_ReferencedValue extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    // GET THE PARAMETERS AND INITIALIZE RESULTING LIST
    List<Object> parameters = (List<Object>) input;
    CapellaElement element_p = (CapellaElement) parameters.get(0);
    EClass valueType = (EClass) parameters.get(1);
    EClass validExpressionType = (EClass) parameters.get(2);
    List<CapellaElement> result = new ArrayList<CapellaElement>();
    // GET AVAILABLE ELEMENTS
    // get all data in the root data package of each allocated architectures
    BlockArchitecture currentBlockArchitecture = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    for (BlockArchitecture blockArchitecture : BlockArchitectureExt.getAllAllocatedArchitectures(currentBlockArchitecture)) {
      DataPkg dataPkg = blockArchitecture.getOwnedDataPkg();
      if (dataPkg != null) {
        result.addAll(getDataFromLevel(dataPkg, element_p, valueType, validExpressionType));
      }
    }
    // get all data and primitive types in the data packages of each parents components
    for (Component cpnt : CapellaElementExt.getComponentHierarchy(element_p)) {
      DataPkg dataPkg = cpnt.getOwnedDataPkg();
      if (dataPkg != null) {
        result.addAll(getDataFromLevel(dataPkg, element_p, valueType, validExpressionType));
        List<CapellaElement> allTypes = new ArrayList<CapellaElement>();
        allTypes.addAll(DataPkgExt.getAllTypesFromDataPkg(dataPkg));
        allTypes = QueryInterpretor.executeFilter(allTypes, new KeepPrimitiveClassInstanceOfSpecificEClassFilter(InformationPackage.Literals.CLASS));
        allTypes = QueryInterpretor.executeFilter(allTypes, new KeepPrimitiveClassInstanceOfSpecificEClassFilter(InformationPackage.Literals.COLLECTION));
        result.addAll(allTypes);
      }
    }
    // get all values in the data packages of each parent components of realized components
    Component currentCpnt =
        (element_p instanceof Component) ? (Component) element_p : (Component) EcoreUtil2.getFirstContainer(element_p, CsPackage.Literals.COMPONENT);
    if (currentCpnt != null) {
      for (Component allocatedCpnt : currentCpnt.getAllocatedComponents()) {
        List<Component> componentHierarchy = CapellaElementExt.getComponentHierarchy(allocatedCpnt);
        componentHierarchy.add(allocatedCpnt);
        for (Component cpnt : componentHierarchy) {
          DataPkg dataPkg = cpnt.getOwnedDataPkg();
          if (dataPkg != null) {
            for (CapellaElement data : getDataFromLevel(dataPkg, element_p, valueType, validExpressionType)) {
              if (!result.contains(data)) {
                result.add(data);
              }
            }
          }
        }
      }
    }
    result = QueryInterpretor.executeFilter(result, new RemoveUnnamedElementFilter());
    return (List) result;
  }

  public List<CapellaElement> getDataFromLevel(DataPkg dataPkg_p, CapellaElement capellaElement_p, EClass valueType, EClass validExpressionType) {
    List<CapellaElement> returnValue = new ArrayList<CapellaElement>();
    if (capellaElement_p instanceof DataValue) {
      DataValue dataValue = (DataValue) capellaElement_p;
      AbstractType type = dataValue.getAbstractType();
      if (null == type) {
        for (CapellaElement element : CapellaElementsHelperForBusinessQueries.getDataValuesInstancesOf(dataPkg_p, valueType, true, true)) {
          if (!(element instanceof AbstractExpressionValue)) {
            returnValue.add(element);
          } else {
            DataType expressionType = ((AbstractExpressionValue) element).getExpressionType();
            if (null == expressionType) {
              returnValue.add(element);
            } else if (validExpressionType.isInstance(expressionType)) {
              returnValue.add(element);
            }
          }
        }
      } else if (type instanceof GeneralizableElement) {
        returnValue = CapellaElementsHelperForBusinessQueries.getValuesTypedBy(dataPkg_p, (GeneralizableElement) type, true, true, valueType, capellaElement_p);
      }
    }
    if (returnValue.contains(capellaElement_p)) {
      int indexOfCurrentElement = returnValue.indexOf(capellaElement_p);
      returnValue.remove(indexOfCurrentElement);
    }
    return returnValue;
  }
}