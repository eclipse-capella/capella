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

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Block;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.ComponentArchitecture;
import org.polarsys.capella.core.data.ctx.Capability;
import org.polarsys.capella.core.data.ctx.CapabilityPkg;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.interaction.InteractionFactory;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.CapabilityRealizationPkg;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacommon.AbstractCapabilityPkg;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ProcessorException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

/**
 */
public class StructureSynchronizationProcessor implements IProcessor {

	private static final String START_PAT = " [From '"; //$NON-NLS-1$
	private static final String END_PAT = "']"; //$NON-NLS-1$

	private ModelElement _context = null;

	/**
	 * Refinement target (can be either a 'ComponentArchitecture', or a 'LogicalComponent')
	 */
	private NamedElement _target = null;
	private NamedElement _synchronizedElement = null;
	private boolean _forceScenarioCreation = false;

	public StructureSynchronizationProcessor() {
		/** no op */
	}

	/**
	 * Constructor
	 * 
	 * @param context_p
	 *            the element on which the processing will applied
	 * @param target_p
	 *            the target of the processing
	 */
	public StructureSynchronizationProcessor(NamedElement context_p, NamedElement target_p) {
		this(context_p, target_p, false);
	}

	/**
	 * Constructor
	 * 
	 * @param context_p
	 *            the element on which the processing will applied
	 * @param target_p
	 *            the target of the processing
	 * @param forceScenarioCreation_p
	 */
	public StructureSynchronizationProcessor(NamedElement context_p, NamedElement target_p, boolean forceScenarioCreation_p) {
		setContext(context_p);
		setTarget(target_p);

		_forceScenarioCreation = forceScenarioCreation_p;
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
	 */
	public Object getName() {
		return "structure synchronization"; //$NON-NLS-1$
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#getResult()
	 */
	public Object getResult() {
		return _synchronizedElement;
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(java.util.List)
	 */
	public void setContext(List<ModelElement> context_p) {
		if ((context_p != null) && (context_p.size() > 0)) {
			setContext(context_p.get(0));
		}
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setContext(org.polarsys.capella.core.common.model.CapellaElement)
	 * 
	 * @param context_p
	 *            the element on which the processing will applied
	 */
	public void setContext(ModelElement context_p) {
		_context = context_p;
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#setTarget(org.polarsys.capella.core.common.model.CapellaElement)
	 * 
	 * @param target_p
	 *            the target of the processing
	 */
	public void setTarget(NamedElement target_p) {
		if ((target_p instanceof ComponentArchitecture) || (target_p instanceof LogicalComponent) || (target_p instanceof PhysicalComponent)) {
			_target = target_p;
		}
	}

	/**
	 * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IProcessor#execute()
	 * 
	 * @throws ProcessorException
	 */
	public void execute(IProgressMonitor progressMonitor_p) throws ProcessorException {
		if (_context instanceof CapabilityPkg) {
			CapabilityRealizationPkg pkg = createCapabilityRealizationPkg((CapabilityPkg) _context, _target);
			synchronizeName((CapabilityPkg) _context, pkg);
			_synchronizedElement = pkg;
		} else if (_context instanceof Capability) {
			CapabilityRealization cap = createCapabilityRealization((Capability) _context, _target);
			synchronizeName((Capability) _context, cap);
			_synchronizedElement = cap;
		} else if (_context instanceof CapabilityRealizationPkg) {
			CapabilityRealizationPkg pkg = createCapabilityRealizationPkg((CapabilityRealizationPkg) _context, _target);
			synchronizeName((CapabilityRealizationPkg) _context, pkg);
			_synchronizedElement = pkg;
		} else if (_context instanceof CapabilityRealization) {
			CapabilityRealization cap = createCapabilityRealization((CapabilityRealization) _context, _target);
			synchronizeName((CapabilityRealization) _context, cap);
			_synchronizedElement = cap;
		} else if (_context instanceof Scenario) {
			Scenario sc = createScenario((Scenario) _context, _target);
			synchronizeName((Scenario) _context, sc);
			_synchronizedElement = sc;
		} else {
			throw new ProcessorException("Non supported object type.", this); //$NON-NLS-1$
		}
	}

	/**
	 * @param referenceElt_p
	 *            reference element
	 * @param updatedElt_p
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void updateName(NamedElement referenceElt_p, NamedElement updatedElt_p) {
		if ((referenceElt_p != null) && (updatedElt_p != null)) {
			if (updatedElt_p.getName() != referenceElt_p.getName()) {
				updatedElt_p.setName(referenceElt_p.getName());
			}
		}
	}

	/**
	 * @param referenceElt_p
	 *            reference element
	 * @param updatedElt_p
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(Capability referenceElt_p, CapabilityRealization updatedElt_p) {
		if ((referenceElt_p != null) && (updatedElt_p != null)) {
			updateName(referenceElt_p, updatedElt_p);

			/** calling parent container */
			if (referenceElt_p.eContainer() instanceof CapabilityPkg) {
				synchronizeName((CapabilityPkg) referenceElt_p.eContainer(), (CapabilityRealizationPkg) updatedElt_p.eContainer());
			}
		}
	}

	/**
	 * @param referenceElt_p
	 *            reference element
	 * @param updatedElt_p
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(CapabilityRealization referenceElt_p, CapabilityRealization updatedElt_p) {
		if ((referenceElt_p != null) && (updatedElt_p != null)) {
			updatedElt_p.setName(computeCapabilityRealizationName(referenceElt_p, updatedElt_p));

			/** calling parent container */
			if (referenceElt_p.eContainer() instanceof CapabilityRealizationPkg) {
				synchronizeName((CapabilityRealizationPkg) referenceElt_p.eContainer(), (CapabilityRealizationPkg) updatedElt_p.eContainer());
			}
		}
	}

	/**
	 * @param referenceElt_p
	 *            reference element
	 * @param updatedElt_p
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(CapabilityPkg referenceElt_p, CapabilityRealizationPkg updatedElt_p) {
		if ((referenceElt_p != null) && (updatedElt_p != null)) {
			updateName(referenceElt_p, updatedElt_p);

			/** calling parent container */
			if (referenceElt_p.eContainer() instanceof CapabilityPkg) {
				synchronizeName((CapabilityPkg) referenceElt_p.eContainer(), (CapabilityRealizationPkg) updatedElt_p.eContainer());
			}
		}
	}

	/**
	 * @param referenceElt_p
	 *            reference element
	 * @param updatedElt_p
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(CapabilityRealizationPkg referenceElt_p, CapabilityRealizationPkg updatedElt_p) {
		if ((referenceElt_p != null) && (updatedElt_p != null)) {
			updateName(referenceElt_p, updatedElt_p);

			/** calling parent container */
			if (referenceElt_p.eContainer() instanceof CapabilityRealizationPkg) {
				synchronizeName((CapabilityRealizationPkg) referenceElt_p.eContainer(), (CapabilityRealizationPkg) updatedElt_p.eContainer());
			}
		}
	}

	/**
	 * @param referenceElt_p
	 *            reference element
	 * @param updatedElt_p
	 *            element whose name will be updated according to the given
	 *            reference element
	 */
	private void synchronizeName(Scenario referenceElt_p, Scenario updatedElt_p) {
		if ((referenceElt_p != null) && (updatedElt_p != null)) {
			updatedElt_p.setName(computeScenarioName(referenceElt_p, updatedElt_p));

			/** calling parent container */
			if (_context.eContainer() instanceof Capability) {
				synchronizeName((Capability) _context.eContainer(), (CapabilityRealization) updatedElt_p.eContainer());
			} else if (_context.eContainer() instanceof CapabilityRealization) {
				synchronizeName((CapabilityRealization) _context.eContainer(), (CapabilityRealization) updatedElt_p.eContainer());
			}
		}
	}

	/**
	 * @param capPkg_p
	 * @param target_p
	 */
	private CapabilityRealizationPkg createCapabilityRealizationPkg(CapabilityPkg capPkg_p, NamedElement target_p) {
		boolean recentCreation = false;
		CapabilityRealizationPkg capabilityRealizationPkg = null;

		/** search for a linked element */
		List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(capPkg_p, LaPackage.Literals.CAPABILITY_REALIZATION_PKG);
		for (CapellaElement relatedElement : relatedElements) {
			if (EcoreUtil2.isContainedBy(relatedElement, target_p)) {
				capabilityRealizationPkg = (CapabilityRealizationPkg) relatedElement;
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (capabilityRealizationPkg == null) {
			if (!(capPkg_p.eContainer() instanceof CapabilityPkg)) {
				AbstractCapabilityPkg abstractCapaPkg = null;
				// Try to find the Root CapabilityPkg existing but not linked
				if (target_p instanceof BlockArchitecture) {
					abstractCapaPkg = ((BlockArchitecture) target_p).getOwnedAbstractCapabilityPkg();
				} else if (target_p instanceof Block) {
					abstractCapaPkg = ((Block) target_p).getOwnedAbstractCapabilityPkg();
				}
				if (abstractCapaPkg != null)
					capabilityRealizationPkg = (CapabilityRealizationPkg) abstractCapaPkg;
				else {
					capabilityRealizationPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(capPkg_p.getName());
				}
			} else {
				capabilityRealizationPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(capPkg_p.getName());
			}
			recentCreation = true;
		}

		/** attach the retrieved element to its retrieved container */
		if (capPkg_p.eContainer() instanceof CapabilityPkg) {
			CapabilityRealizationPkg parentCapabilityRealizationPkg = createCapabilityRealizationPkg((CapabilityPkg) capPkg_p.eContainer(), target_p);
			parentCapabilityRealizationPkg.getOwnedCapabilityRealizationPkgs().add(capabilityRealizationPkg);
		} else if (target_p instanceof BlockArchitecture) {
			((BlockArchitecture) target_p).setOwnedAbstractCapabilityPkg(capabilityRealizationPkg);
		} else if (target_p instanceof Block) {
			((Block) target_p).setOwnedAbstractCapabilityPkg(capabilityRealizationPkg);
		}

		if ((capabilityRealizationPkg != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(capabilityRealizationPkg, capPkg_p);
		}

		return capabilityRealizationPkg;
	}

	/**
	 * @param capPkg_p
	 * @param target_p
	 */
	private CapabilityRealizationPkg createCapabilityRealizationPkg(CapabilityRealizationPkg capPkg_p, NamedElement target_p) {
		boolean recentCreation = false;
		CapabilityRealizationPkg capabilityRealizationPkg = null;

		if ((capPkg_p.eContainer() instanceof Block || capPkg_p.eContainer() instanceof BlockArchitecture) && target_p instanceof Block) {
			capabilityRealizationPkg = (CapabilityRealizationPkg) ((Block) target_p).getOwnedAbstractCapabilityPkg();
		}

		if (capabilityRealizationPkg == null) {
			/** search for a linked element */
			List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(capPkg_p, LaPackage.Literals.CAPABILITY_REALIZATION_PKG);
			for (CapellaElement relatedElement : relatedElements) {
				if (EcoreUtil2.isContainedBy(relatedElement, target_p)) {
					capabilityRealizationPkg = (CapabilityRealizationPkg) relatedElement;
				}
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (capabilityRealizationPkg == null) {
			if (!(capPkg_p.eContainer() instanceof CapabilityPkg)) {
				AbstractCapabilityPkg abstractCapaPkg = null;
				// Try to find the Root CapabilityPkg existing but not linked
				if (target_p instanceof BlockArchitecture) {
					abstractCapaPkg = ((BlockArchitecture) target_p).getOwnedAbstractCapabilityPkg();
				} else if (target_p instanceof Block) {
					abstractCapaPkg = ((Block) target_p).getOwnedAbstractCapabilityPkg();
				}
				if (abstractCapaPkg != null)
					capabilityRealizationPkg = (CapabilityRealizationPkg) abstractCapaPkg;
				else {
					capabilityRealizationPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(capPkg_p.getName());
				}
			} else {
				capabilityRealizationPkg = LaFactory.eINSTANCE.createCapabilityRealizationPkg(capPkg_p.getName());
			}
			recentCreation = true;
		}

		/** attach the retrieved element to its retrieved container */
		if (capPkg_p.eContainer() instanceof CapabilityRealizationPkg) {
			CapabilityRealizationPkg parentCapabilityRealizationPkg = createCapabilityRealizationPkg((CapabilityRealizationPkg) capPkg_p.eContainer(), target_p);
			parentCapabilityRealizationPkg.getOwnedCapabilityRealizationPkgs().add(capabilityRealizationPkg);
		} else if (target_p instanceof BlockArchitecture) {
			((BlockArchitecture) target_p).setOwnedAbstractCapabilityPkg(capabilityRealizationPkg);
		} else if (target_p instanceof Block) {
			((Block) target_p).setOwnedAbstractCapabilityPkg(capabilityRealizationPkg);
		}

		if ((capabilityRealizationPkg != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(capabilityRealizationPkg, capPkg_p);
		}

		return capabilityRealizationPkg;
	}

	/**
	 * @param capability_p
	 * @param target_p
	 * @param callOwnedUseCase
	 */
	private CapabilityRealization createCapabilityRealization(Capability capability_p, NamedElement target_p) {
		boolean recentCreation = false;
		CapabilityRealization capabilityRealization = null;

		/** search for a linked element */
		List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(capability_p, LaPackage.Literals.CAPABILITY_REALIZATION);
		for (CapellaElement relatedElement : relatedElements) {
			if (EcoreUtil2.isContainedBy(relatedElement, target_p)) {
				capabilityRealization = (CapabilityRealization) relatedElement;
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (capabilityRealization == null) {
			capabilityRealization = LaFactory.eINSTANCE.createCapabilityRealization(capability_p.getName());
			recentCreation = true;
		}

		/** attach the retrieved element to its retrieved container */
		if (capability_p.eContainer() instanceof CapabilityPkg) {
			CapabilityRealizationPkg parentCapabilityRealizationPkg = createCapabilityRealizationPkg((CapabilityPkg) capability_p.eContainer(), target_p);
			parentCapabilityRealizationPkg.getOwnedCapabilityRealizations().add(capabilityRealization);
		}

		if ((capabilityRealization != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(capabilityRealization, capability_p);
		}

		return capabilityRealization;
	}

	/**
	 * @param capabilityReal_p
	 * @param target_p
	 * @param callOwnedUseCase
	 */
	private CapabilityRealization createCapabilityRealization(CapabilityRealization capabilityReal_p, NamedElement target_p) {
		boolean recentCreation = false;
		CapabilityRealization capabilityRealization = null;

		/** search for a linked element */
		List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(capabilityReal_p, LaPackage.Literals.CAPABILITY_REALIZATION);
		for (CapellaElement relatedElement : relatedElements) {
			if (EcoreUtil2.isContainedBy(relatedElement, target_p)) {
				capabilityRealization = (CapabilityRealization) relatedElement;
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (capabilityRealization == null) {
			capabilityRealization = LaFactory.eINSTANCE.createCapabilityRealization(computeCapabilityRealizationName(capabilityReal_p, target_p));
			recentCreation = true;
		}

		/** attach the retrieved element to its retrieved container */
		if (capabilityReal_p.eContainer() instanceof CapabilityRealizationPkg) {
			CapabilityRealizationPkg parentCapabilityRealizationPkg = createCapabilityRealizationPkg((CapabilityRealizationPkg) capabilityReal_p.eContainer(), target_p);
			parentCapabilityRealizationPkg.getOwnedCapabilityRealizations().add(capabilityRealization);
		}

		if ((capabilityRealization != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(capabilityRealization, capabilityReal_p);
		}

		return capabilityRealization;
	}

	/**
	 * Return a Name from the CapabilityRealization given in Parameter
	 * 
	 * @param capabilityReal_p
	 * @param target_p
	 */
	private String computeCapabilityRealizationName(CapabilityRealization capabilityReal_p, NamedElement target_p) {
		StringBuffer newName = new StringBuffer();
		LogicalComponent containerLc = null;

		if (CapellaLayerCheckingExt.isInPhysicalLayer(target_p) || CapellaLayerCheckingExt.isInEPBSLayer(target_p) || target_p instanceof EPBSArchitecture) {
			// Not apply the naming rule in PhysicalLayer refinement case
			return capabilityReal_p.getName();
		}

		containerLc = (LogicalComponent) EcoreUtil2.getFirstContainer(capabilityReal_p, LaPackage.Literals.LOGICAL_COMPONENT);
		if (containerLc == null) {
			// Case : Scenario contained by LogicalArchitecture (not under a Logical Component)
			// Retrieve the Root Logical Component (from System)
			LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(capabilityReal_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
			containerLc = SystemEngineeringExt.getRootLogicalComponent(la);
		}
		String srcCapName = capabilityReal_p.getName();
		int startIndex = srcCapName.indexOf(START_PAT);
		if (startIndex != -1) {
			newName.append(srcCapName.substring(0, startIndex));
		} else
			newName.append(srcCapName);
		newName.append(START_PAT).append(containerLc.getName()).append(END_PAT);

		return newName.toString();
	}

	/**
	 * Return a Name from the Scenario given in Parameter
	 * 
	 * @param scenario_p
	 * @param target_p
	 */
	private String computeScenarioName(Scenario scenario_p, NamedElement target_p) {
		StringBuffer newName = new StringBuffer();
		LogicalComponent containerLc = null;

		// Not apply the naming rule in ContextLayer refinement case
		if (CapellaLayerCheckingExt.isInContextLayer(scenario_p)) {
			return scenario_p.getName();
		}

		// Not apply the naming rule in PhysicalLayer refinement case
		if (CapellaLayerCheckingExt.isInPhysicalLayer(target_p) ||
			CapellaLayerCheckingExt.isInEPBSLayer(target_p) || (target_p instanceof EPBSArchitecture))
		{
			return scenario_p.getName();
		}

		containerLc = (LogicalComponent) EcoreUtil2.getFirstContainer(scenario_p, LaPackage.Literals.LOGICAL_COMPONENT);
		if (containerLc == null) {
			// Case : Scenario contained by LogicalArchitecture (not under a Logical Component)
			// Retrieve the Root Logical Component (from System)
			LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(scenario_p, LaPackage.Literals.LOGICAL_ARCHITECTURE);
			containerLc = SystemEngineeringExt.getRootLogicalComponent(la);
		}
		String srcCapName = scenario_p.getName();
		int startIndex = srcCapName.indexOf(START_PAT);
		if (startIndex != -1) {
			newName.append(srcCapName.substring(0, startIndex));
		} else
			newName.append(srcCapName);
		newName.append(START_PAT).append(containerLc.getName()).append(END_PAT);

		return newName.toString();
	}

	/**
	 * @param scenario_p
	 * @param target_p
	 */
	private Scenario createScenario(Scenario scenario_p, NamedElement target_p) {
		boolean recentCreation = false;
		Scenario scenario = null;

		/** search for a linked element */
		if (!_forceScenarioCreation) {
			List<CapellaElement> relatedElements = RefinementLinkExt.getRefinementRelatedSourceElements(scenario_p, InteractionPackage.Literals.SCENARIO);
			for (CapellaElement relatedElement : relatedElements) {
				if (EcoreUtil2.isContainedBy(relatedElement, target_p)) {
					scenario = (Scenario) relatedElement;
				}
			}
		}

		/** if there is no linked element, create a new one and link it */
		if (scenario == null) {
			scenario = InteractionFactory.eINSTANCE.createScenario(scenario_p.getName());
			scenario.setKind(scenario_p.getKind());
			recentCreation = true;
		}

		/**
		 * attach the retrieved element to its retrieved container
		 * TODO (refinement) if the scenario is moved, its related events must also be moved
		 */
		if (scenario_p.eContainer() instanceof Capability) {
			CapabilityRealization abstractCapabilityContext = createCapabilityRealization((Capability) scenario_p.eContainer(), target_p);
			abstractCapabilityContext.getOwnedScenarios().add(scenario);
		} else if (scenario_p.eContainer() instanceof CapabilityRealization) {
			CapabilityRealization abstractCapabilityContext = createCapabilityRealization((CapabilityRealization) scenario_p.eContainer(), target_p);
			abstractCapabilityContext.getOwnedScenarios().add(scenario);
		}

		if ((scenario != null) && recentCreation) {
			RefinementLinkExt.createRefinementTraceabilityLink(scenario, scenario_p);
		}

		return scenario;
	}
}
