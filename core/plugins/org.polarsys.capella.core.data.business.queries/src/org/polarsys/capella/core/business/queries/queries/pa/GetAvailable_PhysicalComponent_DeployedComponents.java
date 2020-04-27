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
package org.polarsys.capella.core.business.queries.queries.pa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.preferences.CapellaModelPreferencesPlugin;

public class GetAvailable_PhysicalComponent_DeployedComponents extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
  public List<CapellaElement> getAvailableElements(CapellaElement element) {
    PhysicalComponent currentPC = (PhysicalComponent) element;
    List<CapellaElement> availableElements = new ArrayList<>(1);
    List<PhysicalComponent> comps = SystemEngineeringExt.getAllPhysicalComponents(currentPC);
    boolean isMultipleDeploymentAllowed = isMultipleDeploymentAllowed();
    for (PhysicalComponent physicalComponent : comps) {
      if (!(currentPC.getNature().equals(PhysicalComponentNature.BEHAVIOR)
          && physicalComponent.getNature().equals(PhysicalComponentNature.NODE))
          && !(!isMultipleDeploymentAllowed && !physicalComponent.getDeployingLinks().isEmpty())
          && !physicalComponent.equals(currentPC) && !EcoreUtil.isAncestor(physicalComponent, currentPC)) {
        availableElements.add(physicalComponent);
      }
    }
    return availableElements;
  }

	public boolean isMultipleDeploymentAllowed() {
		return CapellaModelPreferencesPlugin.getDefault().isMultipleDeploymentAllowed();
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<>();
		if (element instanceof PhysicalComponent) {
			PhysicalComponent pc = (PhysicalComponent) element;
			for (AbstractDeploymentLink abstractDeployment : pc.getDeploymentLinks()) {
				if (abstractDeployment instanceof TypeDeploymentLink) {
					currentElements.add(abstractDeployment.getDeployedElement());
				}
			}
		}
		return currentElements;
	}
}