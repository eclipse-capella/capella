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

public class GetAvailable_PhysicalActor_ImplementedInterfaces extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    if (null == systemEngineering) {
      return availableElements;
    }
    if (element_p instanceof PhysicalActor) {
      PhysicalActor ele = (PhysicalActor) element_p;
      availableElements.addAll(getRule_MQRY_Service_ItemRealization_11(systemEngineering, ele));
      availableElements.addAll(getRule_MQRY_Actor_UsedInterfaces12(ele, systemEngineering));
    }
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11(SystemEngineering systemEng_p, PhysicalActor actor_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(actor_p);
    if (null != arch) {
      availableElements.addAll(getElementsFromBlockArchitecture(arch, actor_p));
    }
    if (!(arch instanceof OperationalAnalysis)) {
      availableElements.addAll(getRule_MQRY_Service_ItemRealization_11_1(systemEng_p, actor_p));
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
   * @param currentActor_pthe current {@link Actor}
   * @param systemEngineering_pthe {@link SystemEngineering}
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_Actor_UsedInterfaces12(Component currentActor_p, SystemEngineering systemEngineering_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    return availableElements;
  }

  /**
   * get all the Interface categories from 'functionalExchange_p' parent Block Architecture
   * @param currentProperty_p_p
   * @param oa_p
   * @return list of ExchangeCategories
   */
  private List<CapellaElement> getElementsFromBlockArchitecture(BlockArchitecture arch_p, PhysicalActor element_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    availableElements.addAll(InterfacePkgExt.getAllInterfaces(arch_p.getOwnedInterfacePkg()));
    if (arch_p instanceof PhysicalArchitecture) {
      PhysicalActorPkg ownedPhysicalActorPkg = ((PhysicalArchitecture) arch_p).getOwnedPhysicalActorPkg();
      availableElements.addAll(PhysicalActorPkgExt.getAllInterfacesFromPhysicalActorPkg(ownedPhysicalActorPkg));
    }
    return availableElements;
  }

  private List<CapellaElement> getRule_MQRY_Service_ItemRealization_11_1(SystemEngineering systemEng_p, PhysicalActor currentProperty_p) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    BlockArchitecture arch = null;
    arch = SystemEngineeringExt.getRootBlockArchitecture(currentProperty_p);
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(currentProperty_p);
    OperationalAnalysis oa = SystemEngineeringExt.getOwnedOperationalAnalysis(sysEng);
    if (null != oa) {
      availableElements.addAll(getElementsFromBlockArchitecture(oa, currentProperty_p));
    } else {
      SystemAnalysis ca = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
      availableElements.addAll(getElementsFromBlockArchitecture(ca, currentProperty_p));
    }
    if (null != arch) {
      if (((null != oa) && (arch instanceof LogicalArchitecture)) || (arch instanceof PhysicalArchitecture)) {
        SystemAnalysis ctxArch = SystemEngineeringExt.getOwnedSystemAnalysis(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(ctxArch, currentProperty_p));
      }
      if ((arch instanceof PhysicalArchitecture)) {
        LogicalArchitecture logArch = SystemEngineeringExt.getOwnedLogicalArchitecture(sysEng);
        availableElements.addAll(getElementsFromBlockArchitecture(logArch, currentProperty_p));
      }
    }
    return availableElements;
  }

}
