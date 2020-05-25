/*******************************************************************************
 * Copyright (c) 2006, 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.business.queries.queries.ctx;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;

public class GetAvailable_Capability_RealizedCapabilities extends AbstractQuery {

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
    if (element instanceof AbstractCapability) {
      availableElements.addAll(getRule_MQRY_AbstractCapability_AvailableCapabilities_11((AbstractCapability) element));
    }
    return availableElements;
  }

  /**
   * previous level Visibility Layer
   */
  private List<CapellaElement> getRule_MQRY_AbstractCapability_AvailableCapabilities_11(AbstractCapability ele) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
    BlockArchitecture currentBlockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele);
    if (currentBlockArchitecture != null) {
      BlockArchitecture previousBlockArchitecture = BlockArchitectureExt
          .getPreviousBlockArchitecture(currentBlockArchitecture);
      availableElements.addAll(BlockArchitectureExt.getAllCapabilities(previousBlockArchitecture));
    }

    return availableElements;
  }

}