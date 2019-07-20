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
package org.polarsys.capella.core.refinement.static_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import static org.polarsys.capella.core.model.helpers.ModelHelpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.refinement.framework.ui.SelectionWizard;
import org.polarsys.capella.core.refinement.framework.ui.model.SelectionItemNode;
import org.polarsys.capella.core.refinement.framework.ui.model.TargetSelectionItem;
import org.polarsys.capella.core.refinement.processor.StructureSynchronizationProcessor;
import org.polarsys.capella.core.refinement.scenarios.core.StaticRefinement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 * This static refinement will synchronize capabilities structure. It can be
 * applied on any AspectPkg contained element.
 * 
 */
public class StructureSynchronization extends StaticRefinement {

	/**
	 * Default constructor
	 */
	public StructureSynchronization() {
		super();
	}

	/**
	 * Constructor
	 */
	public StructureSynchronization(NamedElement context) {
		super();
		setContext(context);
	}

	/**
	 * Add processors
	 * 
	 * @param context
	 */
	@Override
	public void setContext(ModelElement context) {
		if ((context instanceof AbstractCapabilityPkg) || (EcoreUtil2.isContainedBy(context, CapellacommonPackage.Literals.ABSTRACT_CAPABILITY_PKG))) {
			List<AbstractCapability> abstractCapabilities = new ArrayList<AbstractCapability>();

			if (context instanceof AbstractCapability) {
				abstractCapabilities.add((AbstractCapability) context);
			}
			else if (context instanceof Scenario) {
				abstractCapabilities.add((AbstractCapability) context.eContainer());
			}
			else {
				Set<EObject> scSet = EObjectExt.getAll(context, InteractionPackage.Literals.ABSTRACT_CAPABILITY);
				for (EObject obj : scSet) {
					abstractCapabilities.add((AbstractCapability) obj);
				}
			}

			super.setContext(context);

			fillInTargets(abstractCapabilities);
		}
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getName()
	 */
	public Object getName() {
		return null;
	}

	/**
	 * Add targeted processors
	 * 
	 * @param srcEltSet
	 */
	private void fillInTargets(List<AbstractCapability> srcEltSet) {
		Map<NamedElement, List<NamedElement>> targetMap = evaluateTarget(srcEltSet);
		for (Map.Entry<NamedElement,List<NamedElement>> entry : targetMap.entrySet()) {
			for (NamedElement target : entry.getValue()) {
				addPlug(new StructureSynchronizationProcessor(entry.getKey(), target));
			}
		}
	}

	/**
	 * @param capabilities
	 */
	private Map<NamedElement, List<NamedElement>> evaluateTarget(List<AbstractCapability> capabilities) {
		Map<NamedElement, List<NamedElement>> tmpTargets = new HashMap<NamedElement, List<NamedElement>>();

		for (NamedElement capability : capabilities) {
			List<NamedElement> targetSet = new ArrayList<NamedElement>();
			AbstractCapabilityPkg aspectPkg = AbstractCapabilityExt.getOwnerAbstractCapabilityPkg((AbstractCapability) capability);
			CapellaElement cpnt = (CapellaElement) aspectPkg.eContainer();

			if (cpnt instanceof SystemAnalysis) {
				for (LogicalArchitecture la : SystemEngineeringExt.getAllLogicalArchitecture(capability)) {
					targetSet.add(la);
				}
			}
			

			else if (cpnt instanceof LogicalArchitecture) {
				// Retrieves the logical components involved in Root LC decomposition for current LA
				LogicalComponent rootLc = SystemEngineeringExt.getRootLogicalComponent((LogicalArchitecture) cpnt);
				for (LogicalComponent lc : LogicalComponentExt.getDecompositionLogicalComponentInvolved(rootLc)) {
					targetSet.add(lc);
				}

				/** the physical architecture is added */
				PhysicalArchitecture pa = SystemEngineeringExt.getPhysicalArchitecture(capability);
				if (pa != null)
					targetSet.add(pa);
			}
			else if (cpnt instanceof LogicalComponent) {
				// Retrieves the logical components children of the current logical component
				for (LogicalComponent lc : LogicalComponentExt.getDecompositionLogicalComponentInvolved((LogicalComponent) cpnt)) {
					if (ComponentExt.isComposite(lc)) 
						targetSet.add(lc);
				}
			}
			else if (cpnt instanceof PhysicalArchitecture) {
				/** the EPBS architecture is added */
				EPBSArchitecture epbs = SystemEngineeringExt.getEPBSArchitecture(capability);
				if (epbs != null)
					targetSet.add(epbs);
			}

			tmpTargets.put(capability, targetSet);
		}

		/** a selection wizard is shown when there are several possibilities */
		return selectTarget(tmpTargets);
	}

	/**
	 * @param allTargets
	 */
	private Map<NamedElement, List<NamedElement>> selectTarget(Map<NamedElement, List<NamedElement>> allTargets) {
		Map<NamedElement, List<NamedElement>> finalTargets = new HashMap<NamedElement, List<NamedElement>>();

		if (!choiceIsNeeded(allTargets)) {
			finalTargets = allTargets;
		} else {
			 String message = "Select the target(s) on which static refinement must be launched."; //$NON-NLS-1$
			TargetSelectionItem rootItem = new TargetSelectionItem(allTargets);
			SelectionWizard wizard = new SelectionWizard(rootItem, "Capella wizard", "Refinement target selection", message, true, true); //$NON-NLS-1$ //$NON-NLS-2$
			if (wizard.open() == 0) {
				for (SelectionItemNode item : wizard.getSelectionList()) {
					if (!finalTargets.containsKey(item.getSrc())) {
						finalTargets.put((AbstractCapability) item.getSrc(), new ArrayList<NamedElement>());
					}
					if (item.getData() != null) {
						finalTargets.get(item.getSrc()).add((NamedElement) item.getData());
					}
				}
			}
		}

		return finalTargets;
	}

	/**
	 * @param allTargets
	 */
	private boolean choiceIsNeeded(Map<NamedElement, List<NamedElement>> allTargets) {
		for (Map.Entry<NamedElement,List<NamedElement>> entry : allTargets.entrySet()) {
			if (entry.getValue().size() > 1)
				return true;
		}
		return false;
	}
}
