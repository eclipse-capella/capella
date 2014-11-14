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
package org.polarsys.capella.core.business.queries.la;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 * 
 * @deprecated
 */
@Deprecated
public class LogicalComponentShortcut_ReferencedLC implements IBusinessQuery {

  /**
   * <p>
   * Gets all the Logical Components contained by the Logical Architecture Decomposition package of the current Logical Component Shortcut. (Refer
   * MQRY_LogicalComponent_ReferencedLC_1)
   * </p>
   * <p>
   * Except the currently referenced Logical Component.
   * </p>
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    if (null == systemEngineering) {
      return availableElements;
    }

    // removing the duplicate entries in the list
    availableElements = ListExt.removeDuplicates(availableElements);

    return availableElements;
  }

  /**
   * <p>
   * </p>
   * <p>
   * Refer
   * </p>
   * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement, boolean)
   */
  public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {

    List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
    return currentElements;
  }

	public EClass getEClass() {
		return null;
	}

	public List<EReference> getEStructuralFeatures() {
    return null;
	}
}
