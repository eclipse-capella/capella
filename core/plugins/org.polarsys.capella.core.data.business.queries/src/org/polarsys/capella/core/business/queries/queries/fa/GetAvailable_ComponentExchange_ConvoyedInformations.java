/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.helpers.AbstractExchangeItemPkgExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_ComponentExchange_ConvoyedInformations extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(EObject)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    if (null == systemEngineering) {
      return availableElements;
    }
    availableElements.addAll(getRule_MQRY_Connection_ExchangeItems_11(systemEngineering, element));
    return availableElements;
  }

  /**
   * get the root architecture of the current
   * @param systemEng : SystemEngineering
   * @param connection : Connection
   * @return
   */
  private List<CapellaElement> getRule_MQRY_Connection_ExchangeItems_11(SystemEngineering systemEng, CapellaElement connection) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(connection);
    availableElements.addAll(getElementsFromBlockArchitecture(arch, connection));
    return availableElements;
  }

  /**
   * return the ExchageItem from current and all the above levels
   * @param compExchange
   * @param arch
   * @return
   */
  @SuppressWarnings("unchecked")
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, CapellaElement compExchange) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (arch != null) {
      List<DataPkg> allDataPkgs = DataPkgExt.getAllDataPkgs(compExchange);
      for (DataPkg dataPkg : allDataPkgs) {
        availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(dataPkg));
      }
      List<InterfacePkg> allInterfacePkgs = InterfacePkgExt.getAllInterfacePkgs(compExchange);
      for (InterfacePkg interfacePkg : allInterfacePkgs) {
        availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(interfacePkg));
      }
    }
    return availableElements;
  }

}
