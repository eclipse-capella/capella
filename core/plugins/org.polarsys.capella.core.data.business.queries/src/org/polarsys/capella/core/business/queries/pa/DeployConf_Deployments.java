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
package org.polarsys.capella.core.business.queries.pa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.deployment.DeploymentConfiguration;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.utils.ListExt;

/**
 */
public class DeployConf_Deployments implements IBusinessQuery {

	/**
	 * All the AbstractDeploymentLinks contained into current
	 * PhysicalArchitecture
	 */
	private List<CapellaElement> getRule_MQRY_DeployConf_Deployments_11(DeploymentConfiguration deployment_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		PhysicalArchitecture pa = (PhysicalArchitecture) EcoreUtil2.getFirstContainer(deployment_p, PaPackage.Literals.PHYSICAL_ARCHITECTURE);

		if (null != pa) {
			List<PhysicalComponent> comps = PhysicalArchitectureExt.getAllPhysicalComponents(pa);
			for (PhysicalComponent theComp : comps) {
				availableElements.addAll(theComp.getDeploymentLinks());
			}
		}
		return availableElements;
	}

	/**
	 * get all the deployment links into the current physical architecture
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.common.model.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

		if (element_p instanceof DeploymentConfiguration) {
			availableElements.addAll(getRule_MQRY_DeployConf_Deployments_11((DeploymentConfiguration) element_p));
		}

		availableElements = ListExt.removeDuplicates(availableElements);
		availableElements.removeAll(getCurrentElements(element_p, false));

		return availableElements;
	}

	/**
	 * All the deployment links traced by a DeploymentConfigurationLink
	 * 
	 * @see org.polarsys.capella.core.business.queries.core.business.queries.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.common.model.CapellaElement,
	 *      boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();

		// FIXME to be updated according to meta-model modifications

		return currentElements;
	}

	public EClass getEClass() {
		return DeploymentPackage.Literals.DEPLOYMENT_CONFIGURATION;
	}

	public List<EReference> getEStructuralFeatures() {
    return Collections.singletonList(DeploymentPackage.Literals.DEPLOYMENT_CONFIGURATION__OWNED_DEPLOYMENT_LINKS);
	}
}
