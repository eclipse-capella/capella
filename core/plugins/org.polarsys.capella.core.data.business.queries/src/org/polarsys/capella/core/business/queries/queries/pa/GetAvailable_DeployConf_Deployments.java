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
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_DeployConf_Deployments extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * get all the deployment links into the current physical architecture
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(EObject)
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		if (element instanceof DeploymentConfiguration) {
			availableElements.addAll(getRule_MQRY_DeployConf_Deployments_11((DeploymentConfiguration) element));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * All the AbstractDeploymentLinks contained into current
	 * PhysicalArchitecture
	 */
	private List<CapellaElement> getRule_MQRY_DeployConf_Deployments_11(DeploymentConfiguration deployment) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		PhysicalArchitecture pa = (PhysicalArchitecture) BlockArchitectureExt.getRootBlockArchitecture(deployment);
		if (null != pa) {
			for (Component theComp : BlockArchitectureExt.getAllComponents(pa)) {
			  if (theComp instanceof PhysicalComponent) {
				availableElements.addAll(((PhysicalComponent)theComp).getDeploymentLinks());
			  }
			}
		}
		return availableElements;
	}

	/** 
	 * All the deployment links traced by a DeploymentConfigurationLink
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(EObject,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		return currentElements;
	}

}