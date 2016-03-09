/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalActorPkg;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.PhysicalActorPkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_PhysicalActor_UsedInterfaces extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    if (null == systemEngineering) {
      return availableElements;
    }
    if (element instanceof PhysicalActor) {
      PhysicalActor ele = (PhysicalActor) element;
      availableElements.addAll(getRule_MQRY_Service_ItemRealization_11(systemEngineering, ele));
      availableElements.addAll(getRule_MQRY_Actor_UsedInterfaces12(ele, systemEngineering));
    }
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11(SystemEngineering systemEng, PhysicalActor actor) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(actor);
    if (null != arch) {
      availableElements.addAll(getElementsFromBlockArchitecture(arch, actor));
    }
    if (!(arch instanceof OperationalAnalysis)) {
      availableElements.addAll(getRule_MQRY_Service_ItemRealization_11_1(systemEng, actor));
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the interfaces contained in the interface package and all of its sub packages of the Shared Package.
   * </p>
   * <p>
   * Except the interfaces that are already used by the current actor
   * </p>
   * <p>
   * Refer MQRY_Actor_UsedInterfaces_12
   * </p>
   * @param currentActorthe current {@link Actor}
   * @param systemEngineeringthe {@link SystemEngineering}
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_Actor_UsedInterfaces12(Component currentActor, SystemEngineering systemEngineering) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    return availableElements;
  }

  /**
   * get all the Interface categories from 'functionalExchange' parent Block Architecture
   * @param currentProperty
   * @param oa
   * @return list of ExchangeCategories
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch, PhysicalActor element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    availableElements.addAll(InterfacePkgExt.getAllInterfaces(arch.getOwnedInterfacePkg()));
    if (arch instanceof PhysicalArchitecture) {
      PhysicalActorPkg ownedPhysicalActorPkg = ((PhysicalArchitecture) arch).getOwnedPhysicalActorPkg();
      availableElements.addAll(PhysicalActorPkgExt.getAllInterfacesFromPhysicalActorPkg(ownedPhysicalActorPkg));
    }
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11_1(SystemEngineering systemEng, PhysicalActor currentProperty) {
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
      if (((null != oa) && (arch instanceof LogicalArchitecture)) || (arch instanceof PhysicalArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentProperty));
      }
      if ((arch instanceof PhysicalArchitecture)) {
        LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentProperty));
      }
    }
    return availableElements;
  }

}
