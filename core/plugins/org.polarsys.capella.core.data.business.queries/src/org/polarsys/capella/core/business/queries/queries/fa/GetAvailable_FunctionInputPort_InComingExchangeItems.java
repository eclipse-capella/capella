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
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.AbstractExchangeItemPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.AbstractExchangeItemPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_FunctionInputPort_InComingExchangeItems extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement element = (CapellaElement) input;
    List<Object> availableElements = new ArrayList<>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    if (null == systemEngineering) {
      return availableElements;
    }
    availableElements.addAll(getRule_MQRY_Service_ItemRealization_11(systemEngineering, element));
    return availableElements;
  }

  public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

    if (element instanceof FunctionInputPort) {
      FunctionInputPort ele = (FunctionInputPort) element;
      for (ExchangeItem abstractExchangeItem : ele.getIncomingExchangeItems()) {
        currentElements.add(abstractExchangeItem);
      }
    }
    return currentElements;
  }

  /**
   * @param currentProperty
   * @param oa
   * @return
   */
  @SuppressWarnings("unchecked")
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, CapellaElement currentProperty) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);

    if (arch != null) {
      DataPkg ownedDataPkg = arch.getOwnedDataPkg();
      if (null != ownedDataPkg) {
        // retrieve all the ExchangeItems from all the dataPkgs
        availableElements.addAll((Collection<? extends CapellaElement>) AbstractExchangeItemPkgExt.getAllAbstractExchangeItems(ownedDataPkg));
      }

      Set<EObject> allInterPkg = EObjectExt.getAll(arch, CapellacorePackage.Literals.ABSTRACT_EXCHANGE_ITEM_PKG);
      // retrieve all the ExchangeItems from all the InterfacePkgs
      for (EObject eObject : allInterPkg) {
        if (eObject instanceof AbstractExchangeItemPkg) {
          AbstractExchangeItemPkg intPkg = (AbstractExchangeItemPkg) eObject;
          availableElements.addAll(intPkg.getOwnedExchangeItems());
        }
      }

    }

    return availableElements;
  }

  // same level
  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11(SystemEngineering systemEng, CapellaElement currentProperty) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty);

    availableElements.addAll(getElementsFromBlockArchitecture(arch, currentProperty));

    // For the layer visibility
    if (!(arch instanceof OperationalAnalysis)) {
      availableElements.addAll(getRule_MQRY_Service_ItemRealization_11_1(systemEng, currentProperty));
    }

    return availableElements;
  }

  /*
   * For the layer visibility
   */
  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11_1(SystemEngineering systemEng, CapellaElement currentProperty) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty);

    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentProperty);
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
    if (null != oa) {
      availableElements.addAll(getElementsFromBlockArchitecture(oa, currentProperty));
    } else {
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
      availableElements.addAll(getElementsFromBlockArchitecture(ca, currentProperty));
    }

    if (null != arch) {
      if (((null != oa) && (arch instanceof LogicalArchitecture)) || (arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentProperty));
      }
      if ((arch instanceof PhysicalArchitecture) || (arch instanceof EPBSArchitecture)) {
        LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentProperty));
      }
      if ((arch instanceof EPBSArchitecture)) {
        PhysicalArchitecture physArch = SystemEngineeringExt.getOwnedPhysicalArchitecture(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(physArch, currentProperty));
      }
    }
    return availableElements;
  }
}
