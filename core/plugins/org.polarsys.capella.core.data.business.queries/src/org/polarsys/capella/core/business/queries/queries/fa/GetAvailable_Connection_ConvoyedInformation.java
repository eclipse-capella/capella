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
package org.polarsys.capella.core.business.queries.queries.fa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.model.helpers.AbstractExchangeItemPkgExt;
import org.polarsys.capella.core.model.helpers.DataPkgExt;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_Connection_ConvoyedInformation extends AbstractQuery {

  @SuppressWarnings({ "rawtypes", "unchecked" })
  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    if (null == systemEngineering) {
      return availableElements;
    }
    availableElements.addAll(getRule_MQRY_Connection_ExchangeItems_11(systemEngineering, element_p));
    return availableElements;
  }

  /**
   * get the root architecture of the current
   * @param systemEng_p : SystemEngineering
   * @param connection_p : Connection
   * @return
   */
  private List<CapellaElement> getRule_MQRY_Connection_ExchangeItems_11(SystemEngineering systemEng_p, CapellaElement connection_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(connection_p);
    availableElements.addAll(getElementsFromBlockArchitecture(arch, connection_p));
    return availableElements;
  }

  /**
   * return the ExchageItem from current and all the above levels
   * @param compExchange_p
   * @param arch_p
   * @return
   */
  @SuppressWarnings("unchecked")
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, CapellaElement compExchange_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    if (arch_p != null) {
      List<DataPkg> allDataPkgs = DataPkgExt.getAllDataPkgs(compExchange_p);
      for (DataPkg dataPkg : allDataPkgs) {
        availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(dataPkg));
      }
      List<InterfacePkg> allInterfacePkgs = InterfacePkgExt.getAllInterfacePkgs(compExchange_p);
      for (InterfacePkg interfacePkg : allInterfacePkgs) {
        availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(interfacePkg));
      }
    }
    if (compExchange_p instanceof ComponentExchange) {
      EList<AbstractExchangeItem> convoyedInformations = ((ComponentExchange) compExchange_p).getConvoyedInformations();
      for (AbstractExchangeItem abstractExchangeItem : convoyedInformations) {
        if (null != abstractExchangeItem) {
          availableElements.remove(abstractExchangeItem);
        }
      }
    }
    return availableElements;
  }

}
