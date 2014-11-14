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
package org.polarsys.capella.core.data.helpers.pa.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.cs.AbstractDeploymentLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PhysicalComponentPkg;
import org.polarsys.capella.core.data.pa.deployment.ComponentInstance;
import org.polarsys.capella.core.data.pa.deployment.DeploymentFactory;
import org.polarsys.capella.core.data.pa.deployment.TypeDeploymentLink;

/**
 * PhysicalComponent helpers
 * 
 */
public class ComponentInstanceExt {

	/**
	 * 
	 * 
	 * @param location
	 *            target location of deployment
	 * @param deployedElement
	 *            element to be deployed
	 */

	public static void addDeployedElement(ComponentInstance location, ComponentInstance deployedElement) {
		// FIXME
		TypeDeploymentLink link = DeploymentFactory.eINSTANCE.createTypeDeploymentLink();
		CapellaElement cont = (CapellaElement) location.eContainer().eContainer().eContainer();
		if (cont != null) {
			((PhysicalComponentPkg) cont).getOwnedDeployments().add(link);
		}
		/*
		 * TODO else { cont = location.getOwnerArchitecture();
		 * ((PhysicalArchitecture) cont).addOwnedDeployments(link); }
		 */

		link.setLocation(location);
		link.setDeployedElement(deployedElement);
	}

	/**
	 * 
	 * @param deployedElement
	 *            element to be deployed
	 * @param location
	 *            target location of deployment
	 */
	public static void addDeployerElement(ComponentInstance deployedElement, ComponentInstance location) {
		ComponentInstanceExt.addDeployedElement(location, deployedElement);

	}

	public static boolean isDeployedOn(ComponentInstance location_p, ComponentInstance deployedElement_p) {

		List<AbstractDeploymentLink> deployments = location_p.getDeploymentLinks();

		for (AbstractDeploymentLink abstractDeployment : deployments) {
			if (abstractDeployment.getDeployedElement().equals(deployedElement_p)) {
				return true;
			}
		}

		return false;
	}

	public static List<ComponentInstance> getDeployedElements(ComponentInstance location_p) {
		List<ComponentInstance> deployedElements = new ArrayList<ComponentInstance>(1);
		List<AbstractDeploymentLink> deployments = location_p.getDeploymentLinks();

		for (AbstractDeploymentLink abstractDeployment : deployments) {
			deployedElements.add((ComponentInstance) abstractDeployment.getDeployedElement());
		}
		return deployedElements;
	}

	public static List<ComponentInstance> getDeploymentTargets(ComponentInstance element_p) {
		List<ComponentInstance> deploymentTargets = new ArrayList<ComponentInstance>();
		List<AbstractDeploymentLink> deployments = element_p.getDeployingLinks();

		for (AbstractDeploymentLink abstractDeployment : deployments) {
			deploymentTargets.add((ComponentInstance) abstractDeployment.getLocation());
		}
		return deploymentTargets;
	}

	public static void undeployElement(ComponentInstance location_p, ComponentInstance deployedElement_p) {
		List<AbstractDeploymentLink> elementsToDelete = new ArrayList<AbstractDeploymentLink>();
		List<AbstractDeploymentLink> deployements = location_p.getDeploymentLinks();

		for (AbstractDeploymentLink abstractDeployment : deployements) {
			if (abstractDeployment.getDeployedElement().equals(deployedElement_p)) {
				elementsToDelete.add(abstractDeployment);
			}
		}

		for (AbstractDeploymentLink toDelete : elementsToDelete) {
			toDelete.destroy();
		}
	}
}
