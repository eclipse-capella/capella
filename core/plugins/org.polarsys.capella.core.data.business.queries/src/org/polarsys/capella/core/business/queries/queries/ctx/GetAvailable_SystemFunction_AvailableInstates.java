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
package org.polarsys.capella.core.business.queries.queries.ctx;

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
import org.polarsys.capella.core.data.fa.AbstractFunction;
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

public class GetAvailable_SystemFunction_AvailableInstates extends AbstractQuery {

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
	public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>();
		SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element_p);
		if (null == systemEngineering) {
			return availableElements;
		}
		if (element_p instanceof AbstractFunction) {
			availableElements.addAll(getAvailableElmentsFromCurrentLevel(systemEngineering, element_p));
		}
		return availableElements;
	}

	/** 
	 * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getCurrentElements(org.polarsys.capella.core.data.capellacore.CapellaElement,boolean)
	 */
	public List<CapellaElement> getCurrentElements(CapellaElement element_p, boolean onlyGenerated_p) {
		List<CapellaElement> currentElements = new ArrayList<CapellaElement>();
		if (element_p instanceof AbstractFunction) {
			AbstractFunction ele = (AbstractFunction) element_p;
			EList<State> availableInStates = ele.getAvailableInStates();
			for (State abstractStateMode : availableInStates) {
				currentElements.add(abstractStateMode);
			}
		}
		return currentElements;
	}

	/** 
	 * look for states and modes from Current And Higher Levels
	 * @param systemEng_p
	 * @param ele_p
	 * @return
	 */
	protected List<CapellaElement> getAvailableElmentsFromCurrentLevel(SystemEngineering systemEng_p, CapellaElement ele_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		BlockArchitecture blockArchitecture = SystemEngineeringExt.getRootBlockArchitecture(ele_p);
		if (blockArchitecture instanceof OperationalAnalysis) {
			getElementsFromOperationalAnalysisLayer(ele_p, blockArchitecture, availableElements);
		} else if (blockArchitecture instanceof SystemAnalysis) {
			getElementsFromSystemAnalysisLayer(ele_p, blockArchitecture, availableElements);
		} else if (blockArchitecture instanceof LogicalArchitecture) {
			getElementsFromLogicalLayer(ele_p, blockArchitecture, availableElements);
		} else if (blockArchitecture instanceof PhysicalArchitecture) {
			getElementsFromPhysicalLayer(ele_p, blockArchitecture, availableElements);
		} else if (blockArchitecture instanceof EPBSArchitecture) {
			getElementsFromEPBSLayer(ele_p, blockArchitecture, availableElements);
		}
		return availableElements;
	}

	/** 
	 * Retrieve Components from OperationalAnalysis Layer + look for available modes and states
	 * @param ele_p
	 * @param systemEngineering
	 * @param availableElements_p
	 */
	protected void getElementsFromOperationalAnalysisLayer(CapellaElement ele_p, BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
		OperationalAnalysis oa = (OperationalAnalysis) blockArch_p;
		EntityPkg ownedEntityPkg = oa.getOwnedEntityPkg();
		List<Entity> allEntity = OperationalAnalysisExt.getAllEntity(ownedEntityPkg);
		for (Entity entity : allEntity) {
			availableElements_p.addAll(getElementsFromBlockArchitecture(entity, ele_p));
		}
	}

	/** 
	 * Retrieve Components from  SystemAnslysis Layer + look for available modes and states
	 * @param ele_p
	 * @param blockArch_p
	 * @param availableElements_p
	 */
	protected void getElementsFromSystemAnalysisLayer(CapellaElement ele_p, BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
		SystemAnalysis ca = (SystemAnalysis) blockArch_p;
		SystemContext ownedSystemContext = ca.getOwnedSystemContext();
		availableElements_p.addAll(getElementsFromBlockArchitecture(ownedSystemContext, ele_p));
		System ownedSystem = ca.getOwnedSystem();
		availableElements_p.addAll(getElementsFromBlockArchitecture(ownedSystem, ele_p));
		ActorPkg ownedActorPkg = ca.getOwnedActorPkg();
		List<Actor> allActors = ActorPkgExt.getAllActors(ownedActorPkg);
		for (Actor actor : allActors) {
			availableElements_p.addAll(getElementsFromBlockArchitecture(actor, ele_p));
		}
	}

	/** 
	 * Retrieve Components from Logical layer + look for available modes and states
	 * @param ele_p
	 * @param blockArch_p
	 * @param availableElements_p
	 */
	protected void getElementsFromLogicalLayer(CapellaElement ele_p, BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
		LogicalArchitecture logArch = (LogicalArchitecture) blockArch_p;
		LogicalContext ownedLogicalContext = logArch.getOwnedLogicalContext();
		availableElements_p.addAll(getElementsFromBlockArchitecture(ownedLogicalContext, ele_p));
		List<LogicalComponent> allLCsFromLogicalArchitectureLayer = LogicalArchitectureExt.getAllLCsFromLogicalArchitectureLayer(logArch);
		for (LogicalComponent logicalComponent : allLCsFromLogicalArchitectureLayer) {
			availableElements_p.addAll(getElementsFromBlockArchitecture(logicalComponent, ele_p));
		}
		List<LogicalActor> allLAsFromLAPkg = LogicalActorPkgExt.getAllLAsFromLAPkg(logArch.getOwnedLogicalActorPkg());
		for (LogicalActor logicalActor : allLAsFromLAPkg) {
			availableElements_p.addAll(getElementsFromBlockArchitecture(logicalActor, ele_p));
		}
	}

	/** 
	 * Retrieve Components from Physical layer + look for available modes and states
	 * @param ele_p
	 * @param blockArch_p
	 * @param availableElements_p
	 */
	protected void getElementsFromPhysicalLayer(CapellaElement ele_p, BlockArchitecture blockArch_p, List<CapellaElement> availableElements_p) {
		PhysicalArchitecture phyArch = (PhysicalArchitecture) blockArch_p;
		PhysicalContext ownedPhysicalContext = phyArch.getOwnedPhysicalContext();
		availableElements_p.addAll(getElementsFromBlockArchitecture(ownedPhysicalContext, ele_p));
		List<PhysicalComponent> allPhysicalComponents = PhysicalArchitectureExt.getAllPhysicalComponents(phyArch);
		for (PhysicalComponent physicalComponent : allPhysicalComponents) {
			availableElements_p.addAll(getElementsFromBlockArchitecture(physicalComponent, ele_p));
		}
		List<PhysicalActor> allPAsFromPAPkg = PhysicalActorPkgExt.getAllPAsFromPAPkg((phyArch.getOwnedPhysicalActorPkg()));
		for (PhysicalActor physicalActor : allPAsFromPAPkg) {
			availableElements_p.addAll(getElementsFromBlockArchitecture(physicalActor, ele_p));
		}
	}

	/** 
	 * Retrieve Components form EPBS Layer + look for available modes and states
	 * @param ele_p a model element
	 * @param blockArchitecture_p
	 * @param availableElements_p
	 */
	protected void getElementsFromEPBSLayer(CapellaElement ele_p, BlockArchitecture blockArchitecture_p, List<CapellaElement> availableElements_p) {
		EPBSArchitecture epbsArch = (EPBSArchitecture) blockArchitecture_p;
		EPBSContext ownedEPBSContext = epbsArch.getOwnedEPBSContext();
		availableElements_p.addAll(getElementsFromBlockArchitecture(ownedEPBSContext, ele_p));
		List<ConfigurationItem> allConfigurationItems = SystemEngineeringExt.getAllConfigurationItems(epbsArch);
		for (ConfigurationItem configurationItem : allConfigurationItems) {
			availableElements_p.addAll(getElementsFromBlockArchitecture(configurationItem, ele_p));
		}
	}

	/** 
	 * get all the States and Modes from current Component
	 * @param ele_p
	 * @param comp_p
	 * @return
	 */
	protected List<CapellaElement> getElementsFromBlockArchitecture(Component comp_p, CapellaElement ele_p) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		if (comp_p != null) {
			EList<StateMachine> ownedStateMachines = comp_p.getOwnedStateMachines();
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
		List<CapellaElement> currentElements = getCurrentElements(ele_p, false);
		availableElements.removeAll(currentElements);
		return availableElements;
	}

}