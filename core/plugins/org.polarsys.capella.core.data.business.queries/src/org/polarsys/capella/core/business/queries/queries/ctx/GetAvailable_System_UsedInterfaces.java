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
package org.polarsys.capella.core.business.queries.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.model.helpers.InterfacePkgExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_System_UsedInterfaces extends AbstractQuery {

  @Override
  public List<Object> execute(Object input, IQueryContext context) {
    CapellaElement capellaElement = (CapellaElement) input;
    List<CapellaElement> availableElements = getAvailableElements(capellaElement);
    return (List) availableElements;
  }

  /**
   * <p>
   * Gets all the Interfaces contained in the Interface Package (and all of its sub packages) of the Context Layer
   * </p>
   * <p>
   * Except the interfaces that are already used by the system.
   * </p>
   * <p>
   * Refer MQRY_System_UsedInterfaces_1
   * </p>
   * @see org.polarsys.capella.core.business.queries.capellacore.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
    if (null == systemEngineering) {
      return availableElements;
    }
    if (element instanceof System) {
      System system = (System) element;
      availableElements.addAll(getRule_MQRY_System_UsedInterface_11(systemEngineering, system));
    } else if (element instanceof SystemEngineering) {
      availableElements.addAll(getRule_MQRY_System_UsedInterface_11(systemEngineering, null));
    }
    return availableElements;
  }

  /**
   * <p>
   * Gets all the Interfaces contained in the Interface Package (and all of its sub packages) of the Context Layer (includes SystemEngineering and Shared Pkg)
   * </p>
   * <p>
   * Except the interfaces that are already used by the system.
   * </p>
   * <p>
   * Refer MQRY_System_UsedInterfaces_1
   * </p>
   * @param sysEngthe system engineering
   * @param systemthe system
   * @param usedFlagflag to check used/implemented interface
   * @return list of interfaces
   */
  private List<CapellaElement> getRule_MQRY_System_UsedInterface_11(SystemEngineering sysEng, System system) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
    availableElements.addAll(InterfacePkgExt.getAllInterfaces(SystemEngineeringExt.getOwnedSystemAnalysis(sysEng).getOwnedInterfacePkg()));
    return availableElements;
  }

}
