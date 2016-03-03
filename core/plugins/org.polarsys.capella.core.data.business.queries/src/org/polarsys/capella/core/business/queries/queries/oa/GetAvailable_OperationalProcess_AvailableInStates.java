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
package org.polarsys.capella.core.business.queries.queries.oa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemContext;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EPBSContext;
import org.polarsys.capella.core.data.fa.FunctionalChain;
import org.polarsys.capella.core.data.helpers.ctx.services.ActorPkgExt;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalContext;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalContext;
import org.polarsys.capella.core.model.helpers.LogicalActorPkgExt;
import org.polarsys.capella.core.model.helpers.LogicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.OperationalAnalysisExt;
import org.polarsys.capella.core.model.helpers.PhysicalActorPkgExt;
import org.polarsys.capella.core.model.helpers.PhysicalArchitectureExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

public class GetAvailable_OperationalProcess_AvailableInStates extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<CapellaElement> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
	 */
	public List<CapellaElement> getAvailableElements(CapellaElement element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element instanceof FunctionalChain) {
			availableElements.addAll(getAvailableElmentsFromCurrentLevel(systemEngineering, element));
		}
		return availableElements;
	}

	/** 
	 * look for states and modes from Current And Higher Levels
	 * @param systemEng
	 * @param ele
	 * @return
	 */
	protected List<CapellaElement> getAvailableElmentsFromCurrentLevel(SystemEngineering systemEng, CapellaElement ele) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture blockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele);
		if (blockArchitecture instanceof OperationalAnalysis) {
			getElementsFromOperationalAnalysisLayer(ele, blockArchitecture, availableElements);
		} else if (blockArchitecture instanceof SystemAnalysis) {
			getElementsFromSystemAnalysisLayer(ele, blockArchitecture, availableElements);
		} else if (blockArchitecture instanceof LogicalArchitecture) {
			getElementsFromLogicalLayer(ele, blockArchitecture, availableElements);
		} else if (blockArchitecture instanceof PhysicalArchitecture) {
			getElementsFromPhysicalLayer(ele, blockArchitecture, availableElements);
		} else if (blockArchitecture instanceof EPBSArchitecture) {
			getElementsFromEPBSLayer(ele, blockArchitecture, availableElements);
		}
		return availableElements;
	}

	/** 
	 * Retrieve Components from OperationalAnalysis Layer + look for available modes and states
	 * @param ele
	 * @param systemEngineering
	 * @param availableElements
	 */
	protected void getElementsFromOperationalAnalysisLayer(CapellaElement ele, BlockArchitecture blockArch, List<CapellaElement> availableElements) {
		OperationalAnalysis oa = (OperationalAnalysis) blockArch;
		EntityPkg ownedEntityPkg = oa.getOwnedEntityPkg();
		List<Entity> allEntity = OperationalAnalysisExt.getAllEntity(ownedEntityPkg);
		for (Entity entity : allEntity) {
			availableElements.addAll(getElementsFromBlockArchitecture(entity, ele));
		}
	}

	/** 
	 * Retrieve Components from  SystemAnslysis Layer + look for available modes and states
	 * @param ele
	 * @param blockArch
	 * @param availableElements
	 */
	protected void getElementsFromSystemAnalysisLayer(CapellaElement ele, BlockArchitecture blockArch, List<CapellaElement> availableElements) {
		SystemAnalysis ca = (SystemAnalysis) blockArch;
		SystemContext ownedSystemContext = ca.getOwnedSystemContext();
		availableElements.addAll(getElementsFromBlockArchitecture(ownedSystemContext, ele));
		System ownedSystem = ca.getOwnedSystem();
		availableElements.addAll(getElementsFromBlockArchitecture(ownedSystem, ele));
		ActorPkg ownedActorPkg = ca.getOwnedActorPkg();
		List<Actor> allActors = ActorPkgExt.getAllActors(ownedActorPkg);
		for (Actor actor : allActors) {
			availableElements.addAll(getElementsFromBlockArchitecture(actor, ele));
		}
	}

	/** 
	 * Retrieve Components from Logical layer + look for available modes and states
	 * @param ele
	 * @param blockArch
	 * @param availableElements
	 */
	protected void getElementsFromLogicalLayer(CapellaElement ele, BlockArchitecture blockArch, List<CapellaElement> availableElements) {
		LogicalArchitecture logArch = (LogicalArchitecture) blockArch;
		LogicalContext ownedLogicalContext = logArch.getOwnedLogicalContext();
		availableElements.addAll(getElementsFromBlockArchitecture(ownedLogicalContext, ele));
		List<LogicalComponent> allLCsFromLogicalArchitectureLayer = LogicalArchitectureExt.getAllLCsFromLogicalArchitectureLayer(logArch);
		for (LogicalComponent logicalComponent : allLCsFromLogicalArchitectureLayer) {
			availableElements.addAll(getElementsFromBlockArchitecture(logicalComponent, ele));
		}
		List<LogicalActor> allLAsFromLAPkg = LogicalActorPkgExt.getAllLAsFromLAPkg(logArch.getOwnedLogicalActorPkg());
		for (LogicalActor logicalActor : allLAsFromLAPkg) {
			availableElements.addAll(getElementsFromBlockArchitecture(logicalActor, ele));
		}
	}

	/** 
	 * Retrieve Components from Physical layer + look for available modes and states
	 * @param ele
	 * @param blockArch
	 * @param availableElements
	 */
	protected void getElementsFromPhysicalLayer(CapellaElement ele, BlockArchitecture blockArch, List<CapellaElement> availableElements) {
		PhysicalArchitecture phyArch = (PhysicalArchitecture) blockArch;
		PhysicalContext ownedPhysicalContext = phyArch.getOwnedPhysicalContext();
		availableElements.addAll(getElementsFromBlockArchitecture(ownedPhysicalContext, ele));
		List<PhysicalComponent> allPhysicalComponents = PhysicalArchitectureExt.getAllPhysicalComponents(phyArch);
		for (PhysicalComponent physicalComponent : allPhysicalComponents) {
			availableElements.addAll(getElementsFromBlockArchitecture(physicalComponent, ele));
		}
		List<PhysicalActor> allPAsFromPAPkg = PhysicalActorPkgExt.getAllPAsFromPAPkg((phyArch.getOwnedPhysicalActorPkg()));
		for (PhysicalActor physicalActor : allPAsFromPAPkg) {
			availableElements.addAll(getElementsFromBlockArchitecture(physicalActor, ele));
		}
	}

	/** 
	 * Retrieve Components form EPBS Layer + look for available modes and states
	 * @param ele a model element
	 * @param blockArchitecture
	 * @param availableElements
	 */
	protected void getElementsFromEPBSLayer(CapellaElement ele, BlockArchitecture blockArchitecture, List<CapellaElement> availableElements) {
		EPBSArchitecture epbsArch = (EPBSArchitecture) blockArchitecture;
		EPBSContext ownedEPBSContext = epbsArch.getOwnedEPBSContext();
		availableElements.addAll(getElementsFromBlockArchitecture(ownedEPBSContext, ele));
		List<ConfigurationItem> allConfigurationItems = SystemEngineeringExt.getAllConfigurationItems(epbsArch);
		for (ConfigurationItem configurationItem : allConfigurationItems) {
			availableElements.addAll(getElementsFromBlockArchitecture(configurationItem, ele));
		}
	}

	/** 
	 * get all the States and Modes from current Component
	 * @param ele
	 * @param comp
	 * @return
	 */
	protected List<CapellaElement> getElementsFromBlockArchitecture(Component comp, CapellaElement ele) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (comp != null) {
			EList<StateMachine> ownedStateMachines = comp.getOwnedStateMachines();
			for (StateMachine stateMachine : ownedStateMachines) {
				TreeIterator<Object> allContents = EcoreUtil.getAllContents(stateMachine, false);
				while (allContents.hasNext()) {
					Object object = allContents.next();
					if (object instanceof State) {
						availableElements.add((CapellaElement) object);
					}
				}
			}
		}
		List<CapellaElement> currentElements = getCurrentElements(ele, false);
		availableElements.removeAll(currentElements);
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element, boolean onlyGenerated) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element instanceof FunctionalChain) {
			FunctionalChain ele = (FunctionalChain) element;
			EList<State> availableInStates = ele.getAvailableInStates();
			for (State abstractStateMode : availableInStates) {
				currentElements.add(abstractStateMode);
			}
		}
		return currentElements;
	}

}