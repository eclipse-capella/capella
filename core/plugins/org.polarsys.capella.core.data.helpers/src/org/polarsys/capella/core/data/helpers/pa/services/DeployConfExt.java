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


/**
 * Class helpers
 * 
 */
public class DeployConfExt {

	// TODO to be updated according to meta-model modifications
//	public static void addDeploymentConfLink(DeploymentConfiguration element, AbstractDeployment deploymentLink) {
//		
//		DeploymentConfLink link = PaFactory.eINSTANCE.createDeploymentConfLink();
//		
//		element.getDeployments().add(link);
//		link.setDeploymentLink(deploymentLink);
//		
//
//	}
	
	// TODO to be updated according to meta-model modifications
//	public static boolean isInConf(DeploymentConfiguration element, AbstractDeployment deploymentLink) {
//		List <DeploymentConfLink> links = element.getDeployments();
//		
//		for (DeploymentConfLink deploymentConfLink : links) {
//			if(deploymentConfLink.getDeploymentLink().equals(deploymentLink)){
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
	// TODO to be updated according to meta-model modifications
//	public static List<AbstractDeployment> getConfiguredDeployments(DeploymentConfiguration element){
//		List<AbstractDeployment> deployments = new ArrayList<AbstractDeployment>();
//		List <DeploymentConfLink> links = element.getDeployments();
//		
//		for (DeploymentConfLink deploymentConfLink : links) {
//			deployments.add(deploymentConfLink.getDeploymentLink());
//		}
//		
//		return deployments;
//		
//	}

	// TODO to be updated according to meta-model modifications
//	public static void unconnectDeployment(DeploymentConfiguration element,
//			AbstractDeployment deployToUnlink) {
//		List <DeploymentConfLink> links = element.getDeployments();
//		List <DeploymentConfLink> destroyableElements = new ArrayList<DeploymentConfLink>();
//		
//		for (DeploymentConfLink deploymentConfLink : links) {
//			if(deploymentConfLink.getDeploymentLink().equals(deployToUnlink)){
//				destroyableElements.add(deploymentConfLink);
//			}
//		}
//		
//		for (DeploymentConfLink elementToDestroy : destroyableElements) {
//			elementToDestroy.destroy();
//		}
//	}
}
