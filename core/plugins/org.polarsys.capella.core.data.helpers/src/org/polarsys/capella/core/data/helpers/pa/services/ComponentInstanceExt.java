/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

	public static boolean isDeployedOn(ComponentInstance location, ComponentInstance deployedElement) {

		List<AbstractDeploymentLink> deployments = location.getDeploymentLinks();

		for (AbstractDeploymentLink abstractDeployment : deployments) {
			if (abstractDeployment.getDeployedElement().equals(deployedElement)) {
				return true;
			}
		}

		return false;
	}

  public static List<ComponentInstance> getDeployedElements(ComponentInstance location) {
    List<ComponentInstance> deployedElements = new ArrayList<>(1);
    List<AbstractDeploymentLink> deployments = location.getDeploymentLinks();

    for (AbstractDeploymentLink abstractDeployment : deployments) {
      ComponentInstance deployedElement = (ComponentInstance) abstractDeployment.getDeployedElement();
      if (deployedElement != null) {
        deployedElements.add(deployedElement);
      }
    }
    return deployedElements;
  }

	public static List<ComponentInstance> getDeploymentTargets(ComponentInstance element) {
		List<ComponentInstance> deploymentTargets = new ArrayList<>();
		List<AbstractDeploymentLink> deployments = element.getDeployingLinks();

		for (AbstractDeploymentLink abstractDeployment : deployments) {
			ComponentInstance location = (ComponentInstance) abstractDeployment.getLocation();
			if(location != null) {
			  deploymentTargets.add(location);			  
			}
		}
		return deploymentTargets;
	}

	public static void undeployElement(ComponentInstance location, ComponentInstance deployedElement) {
		List<AbstractDeploymentLink> elementsToDelete = new ArrayList<>();
		List<AbstractDeploymentLink> deployements = location.getDeploymentLinks();

		for (AbstractDeploymentLink abstractDeployment : deployements) {
			if (abstractDeployment.getDeployedElement().equals(deployedElement)) {
				elementsToDelete.add(abstractDeployment);
			}
		}

		for (AbstractDeploymentLink toDelete : elementsToDelete) {
			toDelete.destroy();
		}
	}
}
