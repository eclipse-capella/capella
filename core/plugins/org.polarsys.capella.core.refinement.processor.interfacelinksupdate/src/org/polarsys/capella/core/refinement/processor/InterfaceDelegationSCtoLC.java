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
package org.polarsys.capella.core.refinement.processor;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.PortRealization;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;

public class InterfaceDelegationSCtoLC {

	protected List<Entry<Component, Component>> pairs = new ArrayList<Entry<Component, Component>>();

	public InterfaceDelegationSCtoLC(LogicalComponent logicalComponent_p) {
		computeComponentPairsForComponentFromLogicalLevel(logicalComponent_p);
	}

	public InterfaceDelegationSCtoLC(LogicalActor logicalActor_p) {
		computeComponentPairsForComponentFromLogicalLevel(logicalActor_p);
	}

	public InterfaceDelegationSCtoLC(LogicalActorPkg logicalActorPck_p) {
		for (Component logicalActor : ActorPkgExt.getAllActors(logicalActorPck_p)) {
			computeComponentPairsForComponentFromLogicalLevel(logicalActor);
		}
	}

	public InterfaceDelegationSCtoLC(System systemComponent) {
		computeComponentPairsForComponentFromSystemLevel(systemComponent);
	}

	public InterfaceDelegationSCtoLC(Actor actor_p) {
		computeComponentPairsForComponentFromSystemLevel(actor_p);
	}
	
	public InterfaceDelegationSCtoLC(ActorPkg actorPck_p) {
		for (Component actor : ActorPkgExt.getAllActors(actorPck_p)) {
			computeComponentPairsForComponentFromSystemLevel(actor);
		}
	}	

	private void computeComponentPairsForComponentFromLogicalLevel(Component logicalComponent_p) {
		for (Component sourceComponent : logicalComponent_p.getAllocatedComponents()) {
			pairs.add(new SimpleEntry<Component, Component>(sourceComponent, logicalComponent_p));
		}
	}

	private void computeComponentPairsForComponentFromSystemLevel(Component systemComponent_p) {
		for (Component targetComponent : systemComponent_p.getAllocatingComponents()) {
			pairs.add(new SimpleEntry<Component, Component>(systemComponent_p, targetComponent));
		}
	}
	
	public void perform() {
		for (Entry<Component, Component> pair : pairs) {
			perform(pair.getKey(), pair.getValue());
		}
	}
	
	protected void perform(Component source, Component target) {
		Collection<EObject> elementsToRemove = new HashSet<EObject>();
		ArrayList<EObject> elementsToUnset = new ArrayList<EObject>();

		// Remove interfaceImplementations which are no longer implemented by
		// system (only to system interfaces, we keep logical interfaces)
		for (InterfaceImplementation inter : target.getImplementedInterfaceLinks()) {
			if (inter.getImplementedInterface() != null) {
				if (!source.getImplementedInterfaces().contains(inter.getImplementedInterface()) && areInSameLayer(inter.getImplementedInterface(), source)) {
					elementsToRemove.add(inter);
				}
			}
		}

		// Create an interfaceImplementation if link was not already created for
		// all implemented interfaces
		for (InterfaceImplementation inter : source.getImplementedInterfaceLinks()) {
			if (inter.getImplementedInterface() != null) {
				ComponentExt.addImplementedInterface(target, inter.getImplementedInterface());
			}
		}

		// Remove interfaceUses which are no longer used by system (only to
		// system interfaces, we keep logical interfaces)
		for (InterfaceUse inter : target.getUsedInterfaceLinks()) {
			if (inter.getUsedInterface() != null) {
				if (!source.getUsedInterfaces().contains(inter.getUsedInterface()) && areInSameLayer(inter.getUsedInterface(), source)) {
					elementsToRemove.add(inter);
				}
			}
		}

		// Create an interfaceUse if link was not already created for all used
		// interfaces
		for (InterfaceUse inter : source.getUsedInterfaceLinks()) {
			if (inter.getUsedInterface() != null) {
				ComponentExt.addUsedInterface(target, inter.getUsedInterface());
			}
		}

		// Create for all ports links between provided and required interfaces
		// and create a port if necessary
		for (Feature feature : source.getOwnedFeatures()) {
			if (feature instanceof ComponentPort) {
				ComponentPort portSource = (ComponentPort) feature;
				ComponentPort portTarget = getRealizingComponentPort(portSource, target);

				// Remove providedInterfaces of target which are no longer
				// provided by system (only to system interfaces, we keep
				// logical interfaces)
				for (Interface i : portTarget.getProvidedInterfaces()) {
					if (!portSource.getProvidedInterfaces().contains(i) && areInSameLayer(i, portSource)) {
						elementsToUnset.add(i);
					}
				}
				for (EObject i : elementsToUnset) {
					portTarget.getProvidedInterfaces().remove(i);
				}
				elementsToUnset.clear();

				// Remove requiredInterfaces of target which are no longer
				// required by system (only to system interfaces, we keep
				// logical interfaces)
				for (Interface i : portTarget.getRequiredInterfaces()) {
					if (!portSource.getRequiredInterfaces().contains(i) && areInSameLayer(i, portSource)) {
						elementsToUnset.add(i);
					}
				}
				for (EObject i : elementsToUnset) {
					portTarget.getRequiredInterfaces().remove(i);
				}
				elementsToUnset.clear();

				// Create provided interface links
				for (Interface i : portSource.getProvidedInterfaces()) {
					if (!portTarget.getProvidedInterfaces().contains(i)) {
						portTarget.getProvidedInterfaces().add(i);
					}
				}

				// Create required interface links
				for (Interface i : portSource.getRequiredInterfaces()) {
					if (!portTarget.getRequiredInterfaces().contains(i)) {
						portTarget.getRequiredInterfaces().add(i);
					}
				}
			}
		}

		// Remove elements to be removed
		for (EObject obj : elementsToRemove) {
			EcoreUtil.delete(obj);
		}
	}

	/**
	 * Returns whether both elements are in the same block architecture
	 */
	private boolean areInSameLayer(EObject source, EObject target) {
		return CapellaLayerCheckingExt.areInSameLayer(source, target);
	}

	/**
	 * Returns the realizingPort of port into the target Create one if not found
	 */
	public ComponentPort getRealizingComponentPort(ComponentPort port, Component target) {

		for (PortRealization realization : port.getIncomingPortRealizations()) {
			Port realizing = realization.getRealizingPort();
			if (realizing != null && realizing instanceof ComponentPort) {
				if (realizing.eContainer().equals(target)) {
					return (ComponentPort) realizing;
				}
			}
		}

		ComponentPort cloned = FaFactory.eINSTANCE.createComponentPort();
		cloned.setOrientation(port.getOrientation());
		cloned.setKind(port.getKind());
		cloned.setName(port.getName());
		target.getOwnedFeatures().add(cloned);

		PortRealization realization = InformationFactory.eINSTANCE.createPortRealization();
		realization.setSourceElement(cloned);
		realization.setTargetElement(port);
		cloned.getOwnedPortRealizations().add(realization);
		return cloned;
	}

}
