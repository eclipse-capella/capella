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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.ReuseLink;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.sharedmodel.SharedPkg;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_ExchangeItemElement_Type extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<EObject> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * <p>
   * Get all datatypes available in the given block architecture and its allocated architectures
   * </p>
   * <p>
   * Except The current type itself
   * </p>
   */
  public List<EObject> getAvailableElements(CapellaElement element) {
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    List<EObject> availableElements = new ArrayList<EObject>();
    boolean isParameterFromSharedPkg = false;
    if (null == systemEngineering) {
      SharedPkg sharedPkg = SystemEngineeringExt.getSharedPkg(element);
      if (sharedPkg != null) {
        for (ReuseLink link : sharedPkg.getReuseLinks()) {
          if (SystemEngineeringExt.getSystemEngineering(link) != null) {
            systemEngineering = SystemEngineeringExt.getSystemEngineering(link);
            isParameterFromSharedPkg = true;
            break;
          }
        }
      }
      if (systemEngineering == null) {
        return availableElements;
      }
    }
    if (element instanceof ExchangeItemElement) {
      ExchangeItemElement parameter = (ExchangeItemElement) element;
      if (!isParameterFromSharedPkg) {
        availableElements.addAll(getRule_MQRY_Parameter_Type_11(parameter));
        availableElements.addAll(getRule_MQRY_Parameter_Type_12(parameter));
      }
    }
    availableElements = ListExt.removeDuplicates(availableElements);
    return availableElements;
  }

  private List<EObject> getRule_MQRY_Parameter_Type_11(ExchangeItemElement currenParameter) {
    List<EObject> allAllocatedDatas = new ArrayList<EObject>();
    BlockArchitecture block = ComponentExt.getRootBlockArchitecture(currenParameter);
    for (BlockArchitecture current : BlockArchitectureExt.getAllAllocatedArchitectures(block)) {
      DataPkg pkg = DataPkgExt.getDataPkgOfBlockArchitecture(current);
      allAllocatedDatas.addAll(DataPkgExt.getAllTypesFromDataPkg(pkg));
    }
    return allAllocatedDatas;
  }

  /** 
	 */
  private List<EObject> getRule_MQRY_Parameter_Type_12(ExchangeItemElement currentParameter) {
    List<EObject> allAllocatedDatas = new ArrayList<EObject>();
    for (Component cpnt : CapellaElementExt.getComponentHierarchy(currentParameter)) {
      DataPkg dataPkg = cpnt.getOwnedDataPkg();
      if (null != dataPkg) {
        allAllocatedDatas.addAll(DataPkgExt.getAllTypesFromDataPkg(dataPkg));
      }
    }
    return allAllocatedDatas;
  }

}
